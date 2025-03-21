package com.example.jwt_0310.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true); // ✅ 쿠키 허용
    config.setAllowedOrigins(List.of("http://localhost:3000")); // ✅ 프론트엔드 주소 허용
    config.setAllowedHeaders(List.of("*")); // ✅ 모든 헤더 허용
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // ✅ 허용할 메서드 설정
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}

