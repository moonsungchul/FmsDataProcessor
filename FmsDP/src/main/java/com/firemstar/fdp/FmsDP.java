package com.firemstar.fdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.firemstar.fdp.core.PulsarThread;
import com.firemstar.fdp.repositories.ArticleRepository;

@SpringBootApplication
@ServletComponentScan
public class FmsDP {
	
	//@Autowired
	//private static ArticleRepository articleDAO;
	
	
	public static void main(String[] args) {
		/*
		final String pulsar_host = "localhost";
		final String pulsar_port = "6650";
		final String pulsar_topic = "newspaper";
		final PulsarThread ct = new PulsarThread("localhost", "6650", "newspaper", 
    			articleDAO);
		Thread t = new Thread(ct, "fms");
		t.start(); */
		//ct.stopThread();
		SpringApplication.run(FmsDP.class, args);
	}

}

