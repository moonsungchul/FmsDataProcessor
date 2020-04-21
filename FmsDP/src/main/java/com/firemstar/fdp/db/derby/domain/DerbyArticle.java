package com.firemstar.fdp.db.derby.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

/**
 	create table Article (
 		id long primary key, 
 		title varchar(1000), 
 		text varchar(5000), 
 		regDate varchar(255)
 	)
 */

@Entity
@Table(name = "ARTICLE")
public class DerbyArticle {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column(length = 1000)
	private String title;
	@Column(length = 5000)
	private String text;
	private String regDate;
	@Column(length = 255)
	private String company;
	

	public DerbyArticle() {
		
	}

	public DerbyArticle(String title, String text, String regDate, String company) {
		super();
		this.title = title;
		this.text = text;
		this.regDate = regDate;
		this.company = company;
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
		if(this.text.length() >= 5000) {
			this.text = this.text.substring(0, 4999);
		}
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "DerbyArticle [id=" + id + ", title=" + title + ", text=" + text + ", regDate=" + regDate + ", company="
				+ company + "]";
	}

}

