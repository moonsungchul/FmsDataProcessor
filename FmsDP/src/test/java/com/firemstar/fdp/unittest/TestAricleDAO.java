package com.firemstar.fdp.unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.firemstar.fdp.db.derby.domain.DerbyArticle;
import com.firemstar.fdp.db.derby.repository.DerbyArticleRepository;


@SpringBootTest
//@DataJpaTest
class TestAricleDAO {
	
	private Logger log = LoggerFactory.getLogger(TestAricleDAO.class);
	

	@Autowired
	private DerbyArticleRepository articleDAO;


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
		log.info(">>>>>>>>>>>>>>> test Article service ....");
		//List<Article> articles = articleImpl.listAll();
		//Llog.info("article : ", articles.toString());
		/*
		DerbyArticle article = new DerbyArticle("Teitle", "Text", "2019");
		DerbyArticle article1 = new DerbyArticle("Teitle2", "Text", "2019");
		DerbyArticle article2 = new DerbyArticle("Teitle3", "Text", "2019");
		//log.info(">>>>>>> " +  entityManager);
		this.articleDAO.save(article);
		this.articleDAO.save(article1);
		this.articleDAO.save(article2); */
		Iterable<DerbyArticle> it = articleDAO.findAll();
		for(DerbyArticle v : it) {
			log.info(">>>>>> article  : " + v.toString());
		}  
	}

}
