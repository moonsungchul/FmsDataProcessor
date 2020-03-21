package com.firemstar.fdp.core.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrStore {
	
	private Logger logger = LoggerFactory.getLogger(SolrStore.class);
	private String url ;
	private String collection;
	private HttpSolrClient solr ;
	
	public SolrStore(String url, String coll) {
		this.url = url;
		this.collection = coll;
		String ss = String.format("%s/%s", this.url, this.collection );
		logger.info("solr url :" + ss);
		this.solr = new HttpSolrClient.Builder(ss).build();
		//this.solr.setParser(new XMLResponseParser());
	}
	
	public void addArticle(SolrArticle art) {
		try {
			this.solr.addBean(art);
			this.solr.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<SolrArticle> searchArticle(String query, int start, int rows) {
		SolrQuery qq = new SolrQuery();
		qq.set("q", query);
		qq.set("start", start);
		qq.set("rows", rows);
		
		try {
			QueryResponse res = solr.query(qq);
			logger.info(">>>>>>>>>>>>> test ");
			List<SolrArticle> ar = res.getBeans(SolrArticle.class);
			logger.info(">>>>>>>>>>>>> test " + ar.toString());
			return ar;
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return new ArrayList<SolrArticle>();
	}
	
	public void deleteArticle(String id) {
		try {
			solr.deleteById(id);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteQuery(String query) {
		try {
			solr.deleteByQuery(query);
			solr.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
