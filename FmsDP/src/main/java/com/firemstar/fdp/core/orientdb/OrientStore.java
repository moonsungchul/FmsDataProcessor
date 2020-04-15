package com.firemstar.fdp.core.orientdb;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class OrientStore {
	
	private OrientDB orient;
	private ODatabaseSession session;
	
	public OrientStore(String host, String user, String passwd, String dbname) {
		String uu = String.format("remote:%s", host);
		this.orient = new OrientDB(uu, OrientDBConfig.defaultConfig());
		this.session = orient.open(dbname, user, passwd);
	}
	
	public void close() {
		if(this.session.isClosed() == false) {
			this.session.close();
		}
		if(this.orient.isOpen() == true) {
			this.orient.close();
		}
	}
	
	public OClass createVertexClass(String cname) {
		if(this.session.getClass(cname) == null) {
			return this.session.createVertexClass(cname);
		} 
		return this.session.getClass(cname);
	}
	
	public OClass createEdgeClass(String cname) {
		if(this.session.getClass(cname) == null) {
			return this.session.createEdgeClass(cname);
		} 
		return this.session.getClass(cname);
	}
	
	public void createProperty(String cname, String pro, OType type) {
		OClass cl = this.session.getClass(cname);
		if(cl == null) return;
		cl.createProperty(pro, type);
	}
	
	public void createIndex(String cname, String index_name, String fname, OClass.INDEX_TYPE type ) {
		OClass cl = this.session.getClass(cname);
		if(cl == null) return;
		cl.createIndex(index_name, type, fname);
	}
	
	
	

}
