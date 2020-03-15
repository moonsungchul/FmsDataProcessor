package com.firemstar.fdp.db.derby.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.firemstar.fdp.db.derby.domain.DerbyArticle;

public interface DerbyArticleRepository extends CrudRepository<DerbyArticle, Long> {

}
