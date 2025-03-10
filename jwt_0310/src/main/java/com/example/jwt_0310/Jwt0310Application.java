package com.example.jwt_0310;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude =SecurityAutoConfiguration.class)
@EnableJpaAuditing
public class Jwt0310Application {

  public static void main(String[] args) {
    SpringApplication.run(Jwt0310Application.class, args);
  }

}
