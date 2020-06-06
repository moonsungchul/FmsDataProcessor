package com.firemstar.fdp.unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.firemstar.fdp.core.PulsarStore;
import com.firemstar.fdp.core.PulsarThread;
import com.firemstar.fdp.core.influxdb.InfluxLoggerCM;
import com.firemstar.fdp.db.derby.domain.DerbyArticle;
import com.firemstar.fdp.db.derby.repository.DerbyArticleRepository;


@DataJpaTest
class TestPulsarThread {
	
	private Logger logger = LoggerFactory.getLogger(TestPulsarThread.class);
	
	@Autowired
	private DerbyArticleRepository articleDAO;
	
	@Autowired
	private InfluxLoggerCM influx;

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

	//@Test
	void test() {
    	PulsarThread ct = new PulsarThread("localhost", "6650", "newspaper", 
    			articleDAO, influx);
    	Thread t = new Thread(ct,"fms");
    	t.start();
    	try {
    		logger.info("thread sleep ....");
			Thread.sleep(50000);
			Iterable<DerbyArticle> it = articleDAO.findAll();
			for(DerbyArticle v : it) {
				logger.info(">>>>>> ret article  : " + v.toString());
			}
			ct.stopThread();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//@Test
	void test2() {
		logger.info(">>>>>>>>>>>>>>>>>>>> test 2 ");
		String host = "192.168.0.31";
		String port = "6650";
		PulsarStore store = new PulsarStore(host, port);
		PulsarClient cl = store.getClient();
		Consumer consumer = store.getConsumer(cl,  "newspaper");
		try {
			consumer.batchReceive();
		} catch (PulsarClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
