package com.firemstar.fdp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firemstar.fdp.db.derby.domain.DerbyArticle;
import com.firemstar.fdp.db.derby.repository.DerbyArticleRepository;


public class DerbyArticleServiceImpl  implements DerbyArticleService {

	@Autowired
    private DerbyArticleRepository articleRepository;

	@Autowired
    public DerbyArticleServiceImpl() {
        //this.articleRepository = articleRepository;
    } 
	
	@Override
	public List<DerbyArticle> listAll() {
		List<DerbyArticle> articles = new ArrayList<>();
		articleRepository.findAll().forEach(articles::add); //fun with Java 8
		return articles;
	}

	@Override
	public Optional<DerbyArticle> getById(Long id) {
		return articleRepository.findById(id);
	}

	@Override
	public DerbyArticle saveOrUpdate(DerbyArticle article) {
		articleRepository.save(article);
		return article;
	}

	@Override
	public void delete(Long id) {
		articleRepository.deleteById(id);
	}

}

