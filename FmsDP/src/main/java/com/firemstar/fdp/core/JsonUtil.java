package com.firemstar.fdp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.firemstar.fdp.db.domain.Article;

public class JsonUtil {
	
	private Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	private ObjectMapper mapper;
	
	public JsonUtil() {
		mapper = new ObjectMapper();
	}
	
	public Article getArticle(String data) {
		Article article = null;
		try {
			article = mapper.readValue(data, Article.class);
		} catch (JsonProcessingException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}
		return article;
	}

}
