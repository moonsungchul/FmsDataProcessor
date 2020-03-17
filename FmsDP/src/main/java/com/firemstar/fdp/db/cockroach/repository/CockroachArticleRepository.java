package com.firemstar.fdp.db.cockroach.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.firemstar.fdp.db.cockroach.domain.CockroachArticle;

public interface CockroachArticleRepository extends CrudRepository<CockroachArticle, Long> {
	
	@Query(value = "select count(*) from CockroachArticle c where c.title = ?1")
	long countTitle(String title);

}
