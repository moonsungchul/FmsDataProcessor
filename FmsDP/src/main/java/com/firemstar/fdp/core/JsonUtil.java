package com.firemstar.fdp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firemstar.fdp.db.derby.domain.DerbyArticle;

public class JsonUtil {
	
	private Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	private ObjectMapper mapper;
	
	public JsonUtil() {
		mapper = new ObjectMapper();
	}
	
	public DerbyArticle getArticle(String data) {
		DerbyArticle article = null;
		try {
			article = mapper.readValue(data, DerbyArticle.class);
		} catch (JsonProcessingException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}
		return article;
	}

}
