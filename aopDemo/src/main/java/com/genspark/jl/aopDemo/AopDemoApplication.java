package com.genspark.jl.aopDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {

		ApplicationContext context= new AnnotationConfigApplicationContext(AppConfig.class);
		Person obj= (Person) context.getBean(Person.class);
		obj.test();
	}

}
