package com.firemstar.fdp.unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.firemstar.fdp.core.JsonUtil;
import com.firemstar.fdp.db.domain.Article;

class TestJsonUtil {
	
	private Logger logger = LoggerFactory.getLogger(TestJsonUtil.class);

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
		JsonUtil jutil = new JsonUtil();
		String data = "{\"id\":1, \"title\":\"title\", \"text\":\"mmmmmm\", \"regDate\":\"2019-12-30\"}";
		Article article = jutil.getArticle(data);
		logger.info(">>>>>> article : " + article);
	}
	
	@Test
	void test2() {
		JsonUtil jsonUtil = new JsonUtil();
		try {
			String fname = "/home/moonstar/work/python_work/PythonTest/FmsNewsCrawler/bin/test.jsonl";
			BufferedReader rp = new BufferedReader(new FileReader(new File(fname)));
			String line = rp.readLine();
			logger.info(line);
			Article article = jsonUtil.getArticle(line);
			logger.info(article.toString());
			rp.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
				
		
	}

}
