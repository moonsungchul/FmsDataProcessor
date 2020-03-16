package com.firemstar.fdp.core;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.firemstar.fdp.db.cockroach.repository.CockroachArticleRepository;
import com.firemstar.fdp.db.derby.domain.DerbyArticle;
import com.firemstar.fdp.db.derby.repository.DerbyArticleRepository;


public class DataProcessThread implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(PulsarThread.class);
	
	private PulsarStore pulsar;
	private String topic;
	private String ip;
	private String port;
	private boolean stop = false;
	private JsonUtil jsonUtil;
	
	//@Autowired
	//private CockroachArticleRepository articleDAO;
	
    public DataProcessThread(String ip, String port, String topic){
    	this.topic = topic;
    	pulsar = new PulsarStore(ip, port);
    	this.ip = ip;
    	this.port = port;
    	jsonUtil = new JsonUtil();
    }
    
    public void stopThread() {
    	stop = false;
    }
	
    @Override
    public void run() {
    	logger.info(">>>>>>>>>>>>>>>> thread run <<<<<<<<<<<<<<<<<");
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

