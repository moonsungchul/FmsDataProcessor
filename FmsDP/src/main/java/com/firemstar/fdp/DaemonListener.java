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
	private ArticleRepository  articleDAO;
	
    private ServletContext sc;
    private final String pulsar_host = "localhost";
    private final String pulsar_port = "6650";
    private final String pulsar_topic = "newspaper";
    private PulsarThread thread;
    
    
    public void startDaemon() {
    	this.thread = new PulsarThread("localhost", "6650", "newspaper", 
    			articleDAO);
    	Thread t = new Thread(this.thread, "fms");
    	t.start();
    }
    
    
    /** 컨텍스트 초기화 시 데몬 스레드를 작동한다 */
    public void contextInitialized (ServletContextEvent event) {
        System.out.println ("== DaemonListener.contextInitialized has been called. ==");
        sc = event.getServletContext();
        startDaemon();
    }
    /** 컨텍스트 종료 시 thread를 종료시킨다 */
    public void contextDestroyed (ServletContextEvent event) {
    	System.out.println ("== DaemonListener.contextDestroyed has been called. ==");
    	this.thread.stopThread();
    }
}
