package com.firemstar.fdp.unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.firemstar.fdp.core.orientdb.OrientStore;

class TestOrient {
	
	private Logger logger = LoggerFactory.getLogger(TestOrient.class);

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
		logger.info(">>>>>>>>>>>> test :");
		String host = "localhost";
		String user = "admin";
		String passwd = "admin";
		String dbname = "TestDB";
		
		OrientStore store = new OrientStore(host, user, passwd, dbname);
		store.createVertexClass("TestVertex");
		store.createVertexClass("TestEdge");
		store.close();
		
	}

}
