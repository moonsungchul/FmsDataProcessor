package com.firemstar.fdp.unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.firemstar.fdp.core.solr.SolrArticle;
//import com.firemstar.fdp.core.solr.SolrStore;

class TestSolrStore {
	
	private Logger logger = LoggerFactory.getLogger(TestSolrStore.class);

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
		logger.info("test");
		/*
		String url = "http://localhost:8983/solr";
		String collection = "newspaper";
		SolrStore store = new SolrStore(url, collection);
		store.deleteQuery("*:*");
		//store.deleteArticle("ed8bfcd4-4e2e-4aa7-aff2-e29abb6ca36b");
		SolrArticle art = new SolrArticle("", "title3", "text", "aa,bb", "aa,bb", "aa,bb", "hanguc", "2020-03-20");
		store.addArticle(art);
		List<SolrArticle> ar = store.searchArticle("*:*", 0, 100);
		for(SolrArticle m : ar) {
			logger.info("@@@@@@@@@@@@@@@@@@@ : " + m.toString());
		}     */
		
	}

}
