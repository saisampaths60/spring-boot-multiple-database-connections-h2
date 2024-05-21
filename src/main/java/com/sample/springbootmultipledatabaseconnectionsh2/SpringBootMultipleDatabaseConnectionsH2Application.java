package com.sample.springbootmultipledatabaseconnectionsh2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
@EnableTransactionManagement
public class SpringBootMultipleDatabaseConnectionsH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultipleDatabaseConnectionsH2Application.class, args);
	}

}
