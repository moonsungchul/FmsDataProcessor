package com.firemstar.fdp.db.derby.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.firemstar.fdp.db.derby.domain.DerbyArticle;

public interface DerbyArticleRepository extends CrudRepository<DerbyArticle, Long> {
	
	@Query(value = "select min(id) from DerbyArticle")
	public long getMinID();

	@Query(value = "select max(id) from DerbyArticle")
	public long getMaxID();
	
	
}
