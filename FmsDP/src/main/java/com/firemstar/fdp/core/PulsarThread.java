package com.firemstar.fdp.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.firemstar.fdp.core.influxdb.InfluxLoggerCM;
import com.firemstar.fdp.db.cockroach.repository.CockroachArticleRepository;
import com.firemstar.fdp.db.derby.domain.DerbyArticle;
import com.firemstar.fdp.db.derby.repository.DerbyArticleRepository;

public class PulsarThread implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(PulsarThread.class);
	
	private PulsarStore pulsar;
	private String topic;
	private String ip;
	private String port;
	private boolean stop = false;
	private JsonUtil jsonUtil;
	private DerbyArticleRepository articleDAO;
	private InfluxLoggerCM influx;
	
	
    public PulsarThread(String ip, String port, String topic, 
    		DerbyArticleRepository dao, InfluxLoggerCM influx){
    	this.topic = topic;
    	pulsar = new PulsarStore(ip, port);
    	this.ip = ip;
    	this.port = port;
    	jsonUtil = new JsonUtil();
    	articleDAO = dao;
    	this.influx = influx;
    	
    }
    
    public void stopThread() {
    	stop = false;
    }
    
    public String getTodayRegDate() {
    	Date from = new Date();
    	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm::ss");
    	return transFormat.format(from);
    }
	
    @Override
    public void run() {
    	this.influx.getLogger().info("PulsarThread", "Start Trhead");
    	PulsarClient cl = pulsar.getClient();
    	Consumer consumer = pulsar.getConsumer(cl,  this.topic);
    	Message msg = null;
    	while(!stop) {
    		try {
    			msg = consumer.receive();
    			DerbyArticle article = jsonUtil.getArticle(new String(msg.getData()));
    			article.setRegDate(getTodayRegDate());
    			article.pretreatment();
    			logger.info(">>>>> receive msg article : " +  article.toString());
    			this.influx.getLogger().info("PulsarThread", "receive msg article : " + article.getTitle());
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
    	this.influx.getLogger().info("PulsarThread", "Stop Trhead");
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

