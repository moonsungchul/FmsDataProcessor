package com.firemstar.fdp.core.orientdb;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;

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
	
	public void createVertexClass(String cname) {
		if(this.session.getClass(cname) == null) {
			this.session.createVertexClass(cname);
		}
	}
	
	public void createEdgeClass(String cname) {
		if(this.session.getClass(cname) == null) {
			this.session.createEdgeClass(cname);
		}
	}
	

}
