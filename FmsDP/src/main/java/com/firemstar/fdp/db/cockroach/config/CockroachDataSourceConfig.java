package com.firemstar.fdp.db.cockroach.config;

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
		entityManagerFactoryRef = "cockroachEntityManagerFactory", 
		transactionManagerRef = "cockroachTransactionManager", 
		basePackages = {"com.firemstar.fdp.db.cockroach.repository"}
)
public class CockroachDataSourceConfig {
	
	@Autowired
	@Qualifier("cockroachDataSource")
	private DataSource cockroachDataSource;
	
	
	@Bean(name = "cockroachEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean cockroachEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(cockroachDataSource)
				.packages("com.firemstar.fdp.db.cockroach.domain")
				.persistenceUnit("cockroach")
				.build();
	}
	
	@Bean(name = "cockroachTransactionManager")
	public PlatformTransactionManager cockroacTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(cockroachEntityManagerFactory(builder).getObject());
	}

}
