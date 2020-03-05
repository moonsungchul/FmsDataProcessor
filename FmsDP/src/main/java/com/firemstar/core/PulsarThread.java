package com.firemstar.core;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarThread implements Runnable {
	
	private PulsarStore pulsar;
	private String topic;
	private String ip;
	private String port;
	private boolean stop = false;
	
    public PulsarThread(String ip, String port, String topic){
    	this.topic = topic;
    	pulsar = new PulsarStore(ip, port);
    	this.ip = ip;
    	this.port = port;
    }
    
    public void stopThread() {
    	stop = false;
    }
	
    @Override
    public void run() {
    	System.out.println("ip : " +  ip);
    	System.out.println("port : " +  port);
    	PulsarClient cl = pulsar.getClient();
    	System.out.println("cl : " +  cl);
    	Consumer consumer = pulsar.getConsumer(cl,  this.topic);
    	System.out.println("consumer : " +  consumer);
    	Message msg = null;
    	while(!stop) {
    		try {
    			msg = consumer.receive();
    			System.out.printf("@@@@@@@@@@@@@@@@@@@@@@@@ Message received: %s\n", new String(msg.getData()));
    			consumer.acknowledge(msg);
    			Thread.sleep(1);
    		} catch (PulsarClientException e1) {
    			e1.printStackTrace();
    			consumer.negativeAcknowledge(msg);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	System.out.println("thread stop!!!!");
		try {
			cl.close();
		} catch (PulsarClientException e1) {
				e1.printStackTrace();
		}
    }
	
    public static void main(String[] args) {
    	PulsarThread ct = new PulsarThread("172.17.0.5", "6650", "newspaper");
    	Thread t = new Thread(ct,"fms");
    	t.start();
    	try {
			Thread.sleep(500000);
			ct.stopThread();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}

