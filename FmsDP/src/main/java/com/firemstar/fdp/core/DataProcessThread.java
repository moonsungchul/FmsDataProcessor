package com.firemstar.fdp.core;

import java.util.List;
import java.util.Optional;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.firemstar.fdp.core.influxdb.InfluxLogger;
import com.firemstar.fdp.core.influxdb.InfluxLoggerCM;
import com.firemstar.fdp.core.solr.SolrArticle;
import com.firemstar.fdp.core.solr.SolrStore;
import com.firemstar.fdp.db.cockroach.domain.CockroachArticle;
import com.firemstar.fdp.db.cockroach.repository.CockroachArticleRepository;
import com.firemstar.fdp.db.derby.domain.DerbyArticle;
import com.firemstar.fdp.db.derby.repository.DerbyArticleRepository;



public class DataProcessThread implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(PulsarThread.class);
	
	private InfluxLoggerCM influx;
	
	private boolean stop = true;
	private final long delay_time = 10000;
	
	private CockroachArticleRepository cockroachDAO;
	private DerbyArticleRepository derbyDAO;
	private SolrStore solrStore;
	
	
    public DataProcessThread(String ip, String port, String topic, 
    		CockroachArticleRepository cockroach, 
    		DerbyArticleRepository derby, String solr_url, String solr_core, InfluxLoggerCM influx){
    	this.cockroachDAO = cockroach;
    	this.derbyDAO = derby;
    	this.solrStore = new SolrStore(solr_url, solr_core);
    	this.influx = influx;
    }
    
    public void stopThread() {
    	stop = false;
    }
	
    @Override
    public void run() {
    	System.out.println(this.influx);
    	this.influx.getLogger().info("DataProcess", "Start DataProcess thread start");
    	HangulParser parser = new HangulParser();
    	while(!Thread.currentThread().isInterrupted()) {
    		if(!this.stop) break;
    		if(this.derbyDAO.count() > 0) {
    			long min_id = this.derbyDAO.getMinID();
    			Optional<DerbyArticle> art =  this.derbyDAO.findById(min_id);
    			if(art.isPresent()) {
    				logger.info(">>>>>> :" + art.get().toString());
    				if(this.cockroachDAO.countTitle(art.get().getTitle()) == 0) {
    					if(art.get().getText() != null &&  art.get().getText().isEmpty() == false) {
    						logger.info("parser : " +  parser);
    						logger.info(">>>>>> :" + art.get().getText().isEmpty());
    						logger.info(">>>>>> :" + art.get().getText());
    						KomoranResultAr retAr = parser.parsing(art.get().getText());
    						CockroachArticle coc = new CockroachArticle(
    							art.get().getTitle(), 
    							art.get().getText(), 
    							art.get().getRegDate(), 
    							String.join(",", retAr.getnArr()), 
    							String.join(",", retAr.getvArr()), 
    							String.join(",", retAr.getVaArr()), 
    							art.get().getCompany());
    						this.cockroachDAO.save(coc);
    						this.saveSolrArticle(coc);
    					
    					} else {
    						logger.info("same data!");
    					}
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
     * 데이터를 solr에 저장한다. 
     * @param art
     */
    public void saveSolrArticle(CockroachArticle art) {
    	
    	String ss = String.format("title:%s", this.filterQuery(art.getTitle()));
    	List<SolrArticle> alist = this.solrStore.searchArticle(ss, 0, 1);
    	logger.info("######## save solr  :" +  ss + " size : " + alist.size());
    	this.influx.getLogger().info("SOLR", "save data size : " + alist.size());
    	if(alist.size() == 0) {
    		SolrArticle obj = new SolrArticle("", art.getTitle(), 
    				art.getText(), art.getNwords(), 
    				art.getVwords(), art.getAwords(), 
    				art.getCompany(), art.getRegDate());
    		obj.arrangment();
    		this.solrStore.addArticle(obj);
    	}
    }
    
    public String filterQuery(String ss) {
    	String val = ss.replace("[", "\\[");
    	val = val.replace("]", "\\]");
    	return val;
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

