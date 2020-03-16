package com.firemstar.fdp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.firemstar.fdp.db.cockroach.repository.CockroachArticleRepository;
//import com.firemstar.fdp.core.PulsarThread;
import com.firemstar.fdp.db.derby.repository.DerbyArticleRepository;


@Component
public class DaemonComp implements ApplicationListener<ContextRefreshedEvent> {
	
	private Logger logger = LoggerFactory.getLogger(DaemonComp.class);
	
	@Autowired
	private DerbyArticleRepository articleDAO;
	
	@Autowired 
	private CockroachArticleRepository cockroachArticleDAO;
	
	@Autowired
	private AppProperies appConfig;

	/*
	private PulsarThread thread;
    	this.thread = new PulsarThread(
    			appConfig.getPulsar().getHost(), 
    			appConfig.getPulsar().getPort(), 
    			appConfig.getPulsar().getTopic(), 
    			articleDAO);
    	Thread t = new Thread(this.thread, "fms");
    	t.start();
    	
    public  */

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.. start pulsar thread");
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.. start pulsar thread" + appConfig.getPulsar().getHost());
		
	}

}
