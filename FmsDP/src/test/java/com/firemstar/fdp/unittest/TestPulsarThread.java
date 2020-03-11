package com.firemstar.fdp.unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.firemstar.fdp.core.PulsarThread;
import com.firemstar.fdp.db.domain.Article;
import com.firemstar.fdp.repositories.ArticleRepository;


@DataJpaTest
class TestPulsarThread {
	
	private Logger logger = LoggerFactory.getLogger(TestPulsarThread.class);
	
	@Autowired
	private ArticleRepository articleDAO;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
    	PulsarThread ct = new PulsarThread("localhost", "6650", "newspaper", 
    			articleDAO);
    	Thread t = new Thread(ct,"fms");
    	t.start();
    	try {
    		logger.info("thread sleep ....");
			Thread.sleep(50000);
			Iterable<Article> it = articleDAO.findAll();
			for(Article v : it) {
				logger.info(">>>>>> ret article  : " + v.toString());
			}
			ct.stopThread();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
