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

import com.firemstar.fdp.db.domain.Article;
import com.firemstar.fdp.repositories.ArticleRepository;

//@SpringBootTest
@DataJpaTest
class TestAricleDAO {
	
	private Logger log = LoggerFactory.getLogger(TestAricleDAO.class);
	
	
	@Autowired
	private TestEntityManager entityManager;

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
		log.info(">>>>>>>>>>>>>>> test Article service ....");
		//List<Article> articles = articleImpl.listAll();
		//Llog.info("article : ", articles.toString());
		Article article = new Article("Teitle", "Text", "2019");
		Article article1 = new Article("Teitle2", "Text", "2019");
		Article article2 = new Article("Teitle3", "Text", "2019");
		//log.info(">>>>>>> " +  entityManager);
		entityManager.persist(article);
		entityManager.persist(article1);
		entityManager.persist(article2);
		Iterable<Article> it = articleDAO.findAll();
		for(Article v : it) {
			log.info(">>>>>> article  : " + v.toString());
		}
	}

}
