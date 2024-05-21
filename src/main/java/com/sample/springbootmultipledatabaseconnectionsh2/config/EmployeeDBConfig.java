package com.sample.springbootmultipledatabaseconnectionsh2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.sample.springbootmultipledatabaseconnectionsh2.repositories.employee", entityManagerFactoryRef = "employeesEntityManagerFactory", transactionManagerRef = "employeesTransactionManager")
public class EmployeeDBConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource employeesDataSource() {
		return DataSourceBuilder.create()
				.driverClassName(environment.getProperty("employees.datasource.driverClassName"))
				.url(environment.getProperty("employees.datasource.url"))
				.username(environment.getProperty("employees.datasource.username"))
				.password(environment.getProperty("employees.datasource.password")).build();
	}

	@Bean
	public PlatformTransactionManager employeesTransactionManager() {
		EntityManagerFactory factory = employeesEntityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean employeesEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(employeesDataSource());
		factory.setPackagesToScan(
				new String[] { "com.sample.springbootmultipledatabaseconnectionsh2.entities.employee" });
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		jpaProperties.put("hibernate.show-sql", environment.getProperty("spring.jpa.show-sql"));
		factory.setJpaProperties(jpaProperties);

		return factory;

	}

}
