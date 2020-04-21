package com.firemstar.fdp.core.orientdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firemstar.fdp.AppProperies;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.metadata.schema.OClass;

@Component
public class OrientStoreSP {
	private AppProperies property;
	private OrientStore orient;
	
	
	@Autowired
	public OrientStoreSP(AppProperies pro) {
		this.property = pro;
		this.orient = new OrientStore(pro.getOrientdb().getHost(), 
				pro.getOrientdb().getUser(),
				pro.getOrientdb().getPasswd(), 
				pro.getOrientdb().getDbname());
	}
	
	public void createSchema() {
		this.orient.createVertexClass("ArticleV");
		this.orient.createEdgeClass("ArticleE");
		this.orient.createVertexClass("WordsV");
		this.orient.createVertexClass("WordsE");
		this.orient.createProperty("ArticleV", "id", OType.STRING);
		this.orient.createProperty("ArticleV", "title", OType.STRING);
		
		this.orient.createProperty("Words", "sid", OType.STRING);
		this.orient.createProperty("Words", "word", OType.STRING);
		
		this.orient.createIndex("ArticleV", "article_id_index", "id", OClass.INDEX_TYPE.UNIQUE);
		this.orient.createIndex("Words", "sid_index", "sid", OClass.INDEX_TYPE.UNIQUE);
		this.orient.createIndex("Words", "word_index", "word", OClass.INDEX_TYPE.UNIQUE);
	}
	
	
	
	

}
