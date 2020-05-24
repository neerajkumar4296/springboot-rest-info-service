package com.weatherinfoservice.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class AppDatabaseConfig {
	
	@Primary	
	@Bean()
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties getDataSourceProperties() {
	    return new DataSourceProperties();
	}
	
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource.configuration")
	public DataSource getUserDataSource() {
	    return getDataSourceProperties().initializeDataSourceBuilder()
	            .type(HikariDataSource.class).build();
	} 
	
	@Bean("userDBTemplate")
	public JdbcTemplate getUserJdbcTemplate() {
		return new JdbcTemplate(getUserDataSource());
		
	}
	
	//uncomment for configuring the other datasource
	
	@Bean
    @ConfigurationProperties(prefix = "spring.seconddatasource")
	public DataSource getSecondDataSource() {
	     return DataSourceBuilder.create().build();
	    }
	
	@Bean("otherDBTemplate")
	public JdbcTemplate getSecondJdbcTemplate() {
		return new JdbcTemplate(getUserDataSource());
		
	}
	
}
