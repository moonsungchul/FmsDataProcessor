package com.firemstar.fdp.core.solr;

import java.util.Arrays;
import java.util.UUID;

import org.apache.solr.client.solrj.beans.Field;

public class SolrArticle {

	@Field
	private String id;
	@Field
	private String title;
	@Field
	private String text;
	@Field
	private String[] nwords;
	@Field
	private String[] vwords;
	@Field
	private String[] awords;
	@Field
	private String company;
	@Field
	private String regDate;
	
	public SolrArticle() {
		
	}
	
	public SolrArticle(String id, String title, String text, 
			String nwords, String vwords, String awords, String company, String regDate) {
		if(id.equals("") == true) {
			UUID one = UUID.randomUUID();
			id = one.toString();
		}
		this.id = id; 
		this.title = title;
		this.text = text;
		this.nwords = nwords.split(",");
		this.vwords = vwords.split(",");
		this.awords = awords.split(",");
		this.company = company;
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getNwords() {
		return nwords;
	}

	public void setNwords(String[] nwords) {
		this.nwords = nwords;
	}

	public String[] getVwords() {
		return vwords;
	}

	public void setVwords(String[] vwords) {
		this.vwords = vwords;
	}

	public String[] getAwords() {
		return awords;
	}

	public void setAwords(String[] awords) {
		this.awords = awords;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "SolrArticle [id=" + id + ", title=" + title + ", text=" + text + ", nwords=" + Arrays.toString(nwords)
				+ ", vwords=" + Arrays.toString(vwords) + ", awords=" + Arrays.toString(awords) + ", company=" + company
				+ ", regDate=" + regDate + "]";
	}
	
	

	
	
	

}
