package com.ca.spring.movie;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.ca.spring.movie")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class JavaConfig {

	@Value("${DATABASE_DRIVER_CLASS_NAME}")
	private String databaseDriverClassName;

	@Value("${DATABASE_URL}")
	private String databaseUrl;

	@Value("${DATABASE_USERNAME}")
	private String databaseUsername;

	@Value("${DATABASE_PASSWORD}")
	private String databasePassword;

	@Value("${ECONOMY_TICKET_PRICE}")
	private int economyTicketPrice;

	@Value("${DELUXE_TICKET_PRICE}")
	private int deluxeTicketPrice;

	@Value("${BALCONY_TICKET_PRICE}")
	private int balconyTicketPrice;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(databaseDriverClassName);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUsername);
		dataSource.setPassword(databasePassword);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "ticketPassValueMap")
	public Map<String, Integer> ticketPassValueMap() {
		Map<String, Integer> ticketPassValueMap = new HashMap<String, Integer>();
		ticketPassValueMap.put("ECONOMY", economyTicketPrice);
		ticketPassValueMap.put("DELUXE", deluxeTicketPrice);
		ticketPassValueMap.put("BALCONY", balconyTicketPrice);
		return ticketPassValueMap;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
