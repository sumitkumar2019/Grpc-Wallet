package com.app.wallet.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This Class Contains Application Context and Bean Configuration
 * 
 * @author Sumit Kumar
 *
 */
@Configuration
@ComponentScan("com.app.wallet.server")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

	@Autowired
	private Environment env;

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);
	
	/**
	 * Bean Configuration for creating Session Factory with Data Source with
	 * hibernate properties
	 * 
	 * @return sessionFactory
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LOGGER.info("Configuring Hibernate Session Factory Bean");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setPackagesToScan("com.app.wallet.server.model");
		LOGGER.info("Hibernate Session Factory Configured Succesfully");
		return sessionFactory;
	}

	/**
	 * Bean Configuration for creating Data Source with Database Properties
	 * 
	 * @return dataSource
	 */
	@Bean
	public DataSource getDataSource() {
		LOGGER.info("Configuring Data Source Bean");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		LOGGER.info("Data Source Configured Succesfully");
		return dataSource;
	}

	/**
	 * Bean Configuration for Hibernate Properties
	 * 
	 * @return properties
	 */
	@Bean
	public Properties hibernateProperties() {
		LOGGER.info("Configuring Hibernate Properties");
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("spring.hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("spring.hibernate.show_sql"));
		properties.put("hibernate.use_sql_comments", env.getProperty("spring.hibernate.use_sql_comments"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.hibernate.ddl-auto"));
		properties.put("hibernate.hbm2ddl.format_sql", env.getProperty("spring.hibernate.format_sql"));
		LOGGER.info("Hibernate Properties Configured Succesfully");
		return properties;
	}

	/**
	 * Bean Configuration for Hibernate Transaction manager
	 * 
	 * @return txManager
	 */
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager() {
		LOGGER.info("Configuring Hibernate Transaction Manager Bean");
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		LOGGER.info("Hibernate Transaction Manager Configured Succesfully");
		return txManager;
	}

}
