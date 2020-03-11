package com.firemstar.fdp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firemstar.fdp.db.domain.Article;
import com.firemstar.fdp.repositories.ArticleRepository;

@Service
public class ArticleServiceImpl  implements ArticleService {

	@Autowired
    private ArticleRepository articleRepository;
	
	/*@Autowired
    public ArticleServiceImpl() {
        this.articleRepository = articleRepository;
    } */
	
	@Override
	public List<Article> listAll() {
		List<Article> articles = new ArrayList<>();
		articleRepository.findAll().forEach(articles::add); //fun with Java 8
		return articles;
	}

	@Override
	public Optional<Article> getById(Long id) {
		return articleRepository.findById(id);
	}

	@Override
	public Article saveOrUpdate(Article article) {
		articleRepository.save(article);
		return article;
	}

	@Override
	public void delete(Long id) {
		articleRepository.deleteById(id);
	}

}

