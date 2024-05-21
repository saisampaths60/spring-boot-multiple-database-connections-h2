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
@EnableJpaRepositories(basePackages = "com.sample.springbootmultipledatabaseconnectionsh2.repositories.department", entityManagerFactoryRef = "departmentsEntityManagerFactory", transactionManagerRef = "departmentsTransactionManager")
public class DepartmentDBConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource departmentsDataSource() {
		return DataSourceBuilder.create()
				.driverClassName(environment.getProperty("departments.datasource.driverClassName"))
				.url(environment.getProperty("departments.datasource.url"))
				.username(environment.getProperty("departments.datasource.username"))
				.password(environment.getProperty("departments.datasource.password")).build();
	}

	@Bean
	public PlatformTransactionManager departmentsTransactionManager() {
		EntityManagerFactory factory = departmentsEntityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean departmentsEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(departmentsDataSource());
		factory.setPackagesToScan(
				new String[] { "com.sample.springbootmultipledatabaseconnectionsh2.entities.department" });
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		jpaProperties.put("hibernate.show-sql", environment.getProperty("spring.jpa.show-sql"));
		factory.setJpaProperties(jpaProperties);

		return factory;

	}

}
