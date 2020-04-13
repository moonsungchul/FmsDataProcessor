package com.firemstar.fdp.unittest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.firemstar.fdp.core.influxdb.InfluxLoggerCM;

@SpringBootTest
public class TestInfluxLoggerCM {

	
	@Autowired
	private InfluxLoggerCM logger;
	
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
		this.logger.getLogger().info("test", "test ok .....");
		this.logger.getLogger().debug("test debug ", "debut test ok");
		
	}
	

}
