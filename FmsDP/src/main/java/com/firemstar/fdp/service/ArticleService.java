package com.firemstar.fdp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;

import com.firemstar.fdp.db.domain.Article;

public interface ArticleService {
    List<Article> listAll();

    Optional<Article> getById(Long id);

    Article saveOrUpdate(Article article);

    void delete(Long id);
}

	