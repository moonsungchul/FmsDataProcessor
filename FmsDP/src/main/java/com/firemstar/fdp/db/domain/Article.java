package com.firemstar.fdp.db.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Entity
public class Article {
	@javax.persistence.Id
	@GeneratedValue
	@Id
	private Long id;
	@Column(length = 1000)
	private String title;
	@Column(length = 5000)
	private String text;
	private String regDate;
    

	public Article() {
		
	}

	public Article(String title, String text, String regDate) {
		super();
		this.title = title;
		this.text = text;
		this.regDate = regDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * tile, text 문자열의 전처리 작업을 수행한다. 
	 */
	public void pretreatment() {
		this.title = this.title.replace("'", "");
		this.text = this.text.replace("'", "");
	}
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", text=" + text + ", regDate=" + regDate + "]";
	}

}

