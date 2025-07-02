package com.customer.ParentApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class ParentApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(ParentApplication.class, args);
//	}

	/*

	Your ParentApplication module:
	•	Is configured as a Maven POM aggregator (<packaging>pom</packaging>)
	•	Has no business logic, controllers, entities, or configs
	•	Is used only to manage dependencies and group modules

    So placing @SpringBootApplication here means:
	•	You have no concrete beans to scan or run
	•	You’ll get classpath scanning issues
	•	It won’t auto-discover beans from other modules unless carefully configured (which is error-prone)

	 */
}
