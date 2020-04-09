package com.firemstar.fdp.unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.firemstar.fdp.core.influxdb.InfluxLogger;

class TestInfluxLogger {

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
		String host = "localhost";
		String port = "28086";
		String user = "root";
		String passwd = "root";
		String dbname = "NewsCrawlerLog";
		
		InfluxLogger logger = new InfluxLogger(host, port, user, passwd, dbname);
		logger.info("TEST INFO", "info test");
		logger.debug("TEST DEBUG", "debug test");
		logger.error("TEST ERROR", "error test");
		logger.warning("TEST WARNING", "warning  test");
				
	}

}
