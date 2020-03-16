package com.firemstar.fdp.db.cockroach.repository;

import org.springframework.data.repository.CrudRepository;

import com.firemstar.fdp.db.cockroach.domain.CockroachArticle;

public interface CockroachArticleRepository extends CrudRepository<CockroachArticle, Long> {

}
