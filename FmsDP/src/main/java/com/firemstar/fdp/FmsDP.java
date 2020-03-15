package com.firemstar.fdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ServletComponentScan
//@EnableJpaRepositories("com")
/*
@EnableJpaRepositories(
		entityManagerFactoryRef = "derbyEntityManagerFactory", 
		transactionManagerRef = "derbyTransactionManager", 
		basePackages = {"com"}
) */
//@EntityScan("com.firemstar.fdp.repositories")   
@EnableJpaRepositories(
		entityManagerFactoryRef = "derbyEntityManagerFactory", 
		transactionManagerRef = "derbyTransactionManager", 
		basePackages="com.firemstar.fdp.db"
)
public class FmsDP {
	
	public static void main(String[] args) {
		SpringApplication.run(FmsDP.class, args);
	}

}

