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
//import com.firemstar.fdp.core.orientdb.OrientStore;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;

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

	//@Test
	void test() {
		logger.info(">>>>>>>>>>>> test :");
		String host = "192.168.0.32";
		String user = "admin";
		String passwd = "admin";
		String dbname = "Newspaper";
		
		OrientStore store = new OrientStore(host, user, passwd, dbname);
		
		//store.createVertexClass("ArticleV");
		store.createVertexClass("WordsV");
		store.createEdgeClass("ArticleE");
		store.createEdgeClass("WordsE");
		
		store.createProperty("ArticleV", "id", OType.STRING);
		store.createProperty("ArticleV", "title", OType.STRING);
		
		store.createProperty("Words", "sid", OType.STRING);
		store.createProperty("Words", "word", OType.STRING);
		
		store.createIndex("ArticleV", "article_id_index", "id", OClass.INDEX_TYPE.UNIQUE);
		store.createIndex("Words", "sid_index", "sid", OClass.INDEX_TYPE.UNIQUE);
		store.createIndex("Words", "word_index", "word", OClass.INDEX_TYPE.UNIQUE);
	
		store.close(); 
		
	}

}
