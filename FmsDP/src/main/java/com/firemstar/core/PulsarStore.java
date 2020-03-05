package com.firemstar.core;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarStore {
	
	private String ip = "";
	private String port = "";
	
	public PulsarStore(String ip, String port) {
		super();
		this.ip = ip;
		this.port = port;
	}
	
	public PulsarClient getClient() {
		try {
			String con = String.format("pulsar://%s:%s", this.ip, this.port);
			PulsarClient client = PulsarClient.builder()
			        .serviceUrl(con)
			        .build();
			return client;
		} catch (PulsarClientException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Consumer getConsumer(PulsarClient cl, String topic) {
		try {
			Consumer consumer = cl.newConsumer()
			        .topic(topic)
			        .subscriptionName("newspaper")
			        .subscribe();
			return consumer;
		} catch (PulsarClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	

}
