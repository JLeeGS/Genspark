package com.genspark.springbootPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Entity;


@SpringBootApplication
@ComponentScan({"com.genspark.springbootPractice.services","com.genspark.springbootPractice.repositories"})
public class SpringbootPracticeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootPracticeApplication.class, args);
	}
}
