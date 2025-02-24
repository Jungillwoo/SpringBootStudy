package com.example.secure_0224;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Secure0224Application {

	public static void main(String[] args) {
		SpringApplication.run(Secure0224Application.class, args);
	}

}
