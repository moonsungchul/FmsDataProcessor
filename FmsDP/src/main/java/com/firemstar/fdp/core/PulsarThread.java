package com.firemstar.fdp.core;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.firemstar.fdp.db.domain.Article;
import com.firemstar.fdp.repositories.ArticleRepository;

public class PulsarThread implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(PulsarThread.class);
	
	private PulsarStore pulsar;
	private String topic;
	private String ip;
	private String port;
	private boolean stop = false;
	private JsonUtil jsonUtil;
	
	@Autowired
	private ArticleRepository articleDAO;
	
    public PulsarThread(String ip, String port, String topic, ArticleRepository dao){
    	this.topic = topic;
    	pulsar = new PulsarStore(ip, port);
    	this.ip = ip;
    	this.port = port;
    	jsonUtil = new JsonUtil();
    	articleDAO = dao;
    }
    
    public void stopThread() {
    	stop = false;
    }
	
    @Override
    public void run() {
    	System.out.println("ip : " +  ip);
    	System.out.println("port : " +  port);
    	PulsarClient cl = pulsar.getClient();
    	System.out.println("cl : " +  cl);
    	Consumer consumer = pulsar.getConsumer(cl,  this.topic);
    	System.out.println("consumer : " +  consumer);
    	Message msg = null;
    	while(!stop) {
    		try {
    			msg = consumer.receive();
    			System.out.printf("@@@@@@@@@@@@@@@@@@@@@@@@ Message received: %s\n", new String(msg.getData()));
    			Article article = jsonUtil.getArticle(new String(msg.getData()));
    			article.pretreatment();
    			logger.info(">>>>> article : " +  article.toString());
    			
    			articleDAO.save(article);
    			consumer.acknowledge(msg);
    			Thread.sleep(1);
    		} catch (PulsarClientException e1) {
    			e1.printStackTrace();
    			consumer.negativeAcknowledge(msg);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	System.out.println("thread stop!!!!");
		try {
			cl.close();
		} catch (PulsarClientException e1) {
				e1.printStackTrace();
		}
    }
   
    /**
     * daa을 데이터베이스에 저장한다. 
     * @param data
     */
    public void saveQueue(String data) {
    	
    }

    /*
    public static void main(String[] args) {
    	PulsarThread ct = new PulsarThread("172.17.0.5", "6650", "newspaper");
    	Thread t = new Thread(ct,"fms");
    	t.start();
    	try {
			Thread.sleep(500000);
			ct.stopThread();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } */

}

