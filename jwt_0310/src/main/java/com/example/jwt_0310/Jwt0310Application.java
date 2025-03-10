package com.example.jwt_0310;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude =SecurityAutoConfiguration.class)
public class Jwt0310Application {

  public static void main(String[] args) {
    SpringApplication.run(Jwt0310Application.class, args);
  }

}
