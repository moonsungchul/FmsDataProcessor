package com.firemstar.fdp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@ConfigurationProperties("app") // prefix app, find app.* values
public class AppProperies {

	private Pulsar pulsar;
	private Solr solr;
	private InfluxDB influxdb;
	private OrientDB orientdb;
	
	public static class OrientDB {
		private String host;
		private String user;
		private String passwd;
		private String dbname;
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		public String getDbname() {
			return dbname;
		}
		public void setDbname(String dbname) {
			this.dbname = dbname;
		}
		
		
	}
	
	public static class InfluxDB {
		
		private String host;
		private String port;
		private String user;
		private String password;
		private String dbname;
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getPort() {
			return port;
		}
		public void setPort(String port) {
			this.port = port;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getDbname() {
			return dbname;
		}
		public void setDbname(String dbname) {
			this.dbname = dbname;
		}
		
		
		
	}
	
	
	public static class Solr {
		private String url;
		private String collection;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getCollection() {
			return collection;
		}
		public void setCollection(String collection) {
			this.collection = collection;
		}
		
		
	}
	

	public static class Pulsar {
		private String host;
		private String port;
		private String topic;
		
		@Override
		public String toString() {
			return "Pulsar [host=" + host + ", port=" + port + ", topic=" + topic + "]";
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getPort() {
			return port;
		}
		public void setPort(String port) {
			this.port = port;
		}
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		
	} // Pulsar

	public Pulsar getPulsar() {
		return pulsar;
	}

	public void setPulsar(Pulsar pulsar) {
		this.pulsar = pulsar;
	}

	public Solr getSolr() {
		return solr;
	}

	public void setSolr(Solr solr) {
		this.solr = solr;
	}

	public InfluxDB getInfluxdb() {
		return influxdb;
	}

	public void setInfluxdb(InfluxDB influxdb) {
		this.influxdb = influxdb;
	}

	public OrientDB getOrientdb() {
		return orientdb;
	}

	public void setOrientdb(OrientDB orientdb) {
		this.orientdb = orientdb;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
