package com.firemstar.fdp.db.derby.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "derbyEntityManagerFactory", 
		transactionManagerRef = "derbyTransactionManager", 
		basePackages = {"com.firemstar.ftp.db.derby"}
)
public class DerbyDataSourceConfig {
	
	@Autowired
	@Qualifier("derbyDataSource")
	private DataSource derbyDataSource;
	
	
	@Primary
	@Bean(name = "derbyEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean derbyEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(derbyDataSource)
				.packages("com.firemstar.fdp.db.derby.domain")
				.persistenceUnit("derby")
				.build();
	}
	
	@Primary
	@Bean(name = "derbyTransactionManager")
	public PlatformTransactionManager derbyTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(derbyEntityManagerFactory(builder).getObject());
	}
	
}
