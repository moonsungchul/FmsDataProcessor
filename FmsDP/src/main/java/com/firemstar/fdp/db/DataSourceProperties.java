package com.firemstar.fdp.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableConfigurationProperties
public class DataSourceProperties {
	
	@Bean(name = "derbyDataSource")
	@Qualifier("derbyDataSoruce")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.derby")
	public DataSource derbyDataSource() {
		//return DataSourceBuilder.create().type(HikariDataSource.class).build();
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "cockroachDataSource")
	@Qualifier("cockroachDataSoruce")
	@ConfigurationProperties(prefix = "spring.datasource.cockroach")
	public DataSource cockroachDataSource() {
		//return DataSourceBuilder.create().type(HikariDataSource.class).build();
		return DataSourceBuilder.create().build();
	}

}
