package com.firemstar.fdp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.firemstar.fdp.db.domain.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
