package com.firemstar.fdp.service;

import java.util.List;
import java.util.Optional;

import com.firemstar.fdp.db.derby.domain.DerbyArticle;


public interface DerbyArticleService {
    List<DerbyArticle> listAll();

    Optional<DerbyArticle> getById(Long id);

    DerbyArticle saveOrUpdate(DerbyArticle article);

    void delete(Long id);
}

	