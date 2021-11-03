package com.FA.Team23.userMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value ="classpath:messages.properties")
public class UserApplicationClass {

	public static void main(String[] args) {
		SpringApplication.run(UserApplicationClass.class, args);
	}

}
