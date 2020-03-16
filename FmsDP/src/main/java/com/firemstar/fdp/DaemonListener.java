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

import com.firemstar.fdp.core.PulsarThread;
import com.firemstar.fdp.db.cockroach.domain.CockroachArticle;
import com.firemstar.fdp.db.cockroach.repository.CockroachArticleRepository;
import com.firemstar.fdp.db.derby.repository.DerbyArticleRepository;

@WebListener
public class DaemonListener implements ServletContextListener{
	
	private Logger logger = LoggerFactory.getLogger(DaemonListener.class);

	@Autowired
	private DerbyArticleRepository articleDAO;
	@Autowired
	private CockroachArticleRepository cockroachArticleDAO;
	@Autowired
	private AppProperies appConfig;
	
	
	//private ServletContext sc;
	private PulsarThread thread;
    
	public void startDaemon() {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.. start pulsar thread");
    	this.thread = new PulsarThread(
    			appConfig.getPulsar().getHost(), 
    			appConfig.getPulsar().getPort(), 
    			appConfig.getPulsar().getTopic(), 
    			articleDAO);
    	Thread t = new Thread(this.thread, "fms");
    	t.start();
    	
    	Iterable<CockroachArticle> ar  = this.cockroachArticleDAO.findAll();
    	logger.info(">>>>>>>>>>>>>>>>>>>>>>> test " +  ar.toString());
    }
	
	
    
    
    public void contextInitialized (ServletContextEvent event) {
        //sc = event.getServletContext();
        startDaemon();
    }
    
    public void contextDestroyed (ServletContextEvent event) {
    	this.thread.stopThread();
    }
}
