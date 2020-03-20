package com.firemstar.fdp.core;

import java.util.Optional;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.firemstar.fdp.db.cockroach.domain.CockroachArticle;
import com.firemstar.fdp.db.cockroach.repository.CockroachArticleRepository;
import com.firemstar.fdp.db.derby.domain.DerbyArticle;
import com.firemstar.fdp.db.derby.repository.DerbyArticleRepository;

import jdk.internal.jline.internal.Log;


public class DataProcessThread implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(PulsarThread.class);
	
	private boolean stop = false;
	private final long delay_time = 10000;
	
	private CockroachArticleRepository cockroachDAO;
	private DerbyArticleRepository derbyDAO;
	
	
    public DataProcessThread(String ip, String port, String topic, 
    		CockroachArticleRepository cockroach, 
    		DerbyArticleRepository derby){
    	this.cockroachDAO = cockroach;
    	this.derbyDAO = derby;
    }
    
    public void stopThread() {
    	stop = false;
    }
	
    @Override
    public void run() {
    	logger.info(">>>>>>>>>>>>>>>> thread run <<<<<<<<<<<<<<<<<");
    	HangulParser parser = new HangulParser();
    	while(!this.stop) {
    		if(this.derbyDAO.count() > 0) {
    			long min_id = this.derbyDAO.getMinID();
    			logger.info(">>>>>> min id " + min_id);
    			Optional<DerbyArticle> art =  this.derbyDAO.findById(min_id);
    			if(art.isPresent()) {
    				logger.info(">>>>>> :" + art.get().toString());
    				if(this.cockroachDAO.countTitle(art.get().getTitle()) == 0) {
    					KomoranResultAr retAr = parser.parsing(art.get().getText());
    					CockroachArticle coc = new CockroachArticle(
    							art.get().getTitle(), 
    							art.get().getText(), 
    							art.get().getRegDate(), 
    							String.join(",", retAr.getnArr()), 
    							String.join(",", retAr.getvArr()), 
    							String.join(",", retAr.getVaArr()));
    					this.cockroachDAO.save(coc);
    				} else {
    					logger.info("same data!");
    				}
    				this.derbyDAO.delete(art.get());
    			}
    		}  else {
    			try {
    				Thread.sleep(this.delay_time);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		} // else 
    	} // while...
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

