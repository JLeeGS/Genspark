package com.genspark.employeedemo.driver;

import com.genspark.employeedemo.domain.Work;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class EmployeeDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context= //new AnnotationConfigApplicationContext(ApplicationConfig.class);
		new ClassPathXmlApplicationContext("config.xml");
		Work obj= (Work) context.getBean("employee");
		obj.work();
	}

}
