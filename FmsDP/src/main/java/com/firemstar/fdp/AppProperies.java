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
	
	
	
	
	
	
}
