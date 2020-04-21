package com.firemstar.fdp;

import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.firemstar.fdp.core.DataProcessThread;
import com.firemstar.fdp.core.PulsarThread;
import com.firemstar.fdp.core.influxdb.InfluxLoggerCM;
import com.firemstar.fdp.db.cockroach.domain.CockroachArticle;
import com.firemstar.fdp.db.cockroach.repository.CockroachArticleRepository;
import com.firemstar.fdp.db.derby.repository.DerbyArticleRepository;

@WebListener
public class DaemonListener implements ServletContextListener{
	
	private Logger logger = LoggerFactory.getLogger(DaemonListener.class);

	@Autowired
	private DerbyArticleRepository derbyArticleDAO;
	@Autowired
	private CockroachArticleRepository cockroachArticleDAO;
	@Autowired
	private AppProperies appConfig;
	
	@Autowired
	private InfluxLoggerCM influx;
	
	
	//private ServletContext sc;
	private PulsarThread thread;
	private DataProcessThread processThread;
    
	public void startDaemon() {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.. start pulsar thread");
    	this.thread = new PulsarThread(
    			appConfig.getPulsar().getHost(), 
    			appConfig.getPulsar().getPort(), 
    			appConfig.getPulsar().getTopic(), 
    			derbyArticleDAO, influx);
    	Thread t = new Thread(this.thread, "derby");
    	t.start();
    	
    	this.processThread = new DataProcessThread(
    			appConfig.getPulsar().getHost(), 
    			appConfig.getPulsar().getPort(), 
    			appConfig.getPulsar().getTopic(), 
    			cockroachArticleDAO,
    			derbyArticleDAO, 
    			appConfig.getSolr().getUrl(), 
    			appConfig.getSolr().getCollection(), this.influx);
    	Thread t2 = new Thread(this.processThread, "cockroach");
    	t2.start();
    	
    }
	
	
    
    
    public void contextInitialized (ServletContextEvent event) {
        //sc = event.getServletContext();
        startDaemon();
    }
    
    public void contextDestroyed (ServletContextEvent event) {
    	this.thread.stopThread();
    	this.processThread.stopThread();
    }
}
