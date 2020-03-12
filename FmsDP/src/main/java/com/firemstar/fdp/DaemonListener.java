package com.firemstar.fdp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.firemstar.fdp.core.PulsarThread;
import com.firemstar.fdp.db.domain.Article;
import com.firemstar.fdp.repositories.ArticleRepository;

@WebListener
public class DaemonListener implements ServletContextListener{
	
	private Logger logger = LoggerFactory.getLogger(DaemonListener.class);

	@Autowired
	private ArticleRepository articleDAO;
	@Autowired
	private AppProperies appConfig;
	
	//private ServletContext sc;
	private PulsarThread thread;
    
	public void startDaemon() {
		
    	this.thread = new PulsarThread(
    			appConfig.getPulsar().getHost(), 
    			appConfig.getPulsar().getPort(), 
    			appConfig.getPulsar().getTopic(), 
    			articleDAO);
    	Thread t = new Thread(this.thread, "fms");
    	t.start();
    }
    
    
    public void contextInitialized (ServletContextEvent event) {
        //sc = event.getServletContext();
        startDaemon();
    }
    
    public void contextDestroyed (ServletContextEvent event) {
    	this.thread.stopThread();
    }
}
