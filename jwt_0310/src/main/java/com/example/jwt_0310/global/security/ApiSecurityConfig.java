package com.example.jwt_0310.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApiSecurityConfig {

  @Bean
  public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception{
    http.csrf(AbstractHttpConfigurer::disable)
      .headers(headers -> headers.frameOptions(
        HeadersConfigurer.FrameOptionsConfig::sameOrigin))
      .securityMatcher("/api/**") // 설정된 경로로 들어오는 모든 것들 검사
      .authorizeHttpRequests( // 요청에 대한 권한을 지정
        authorize -> authorize
          .requestMatchers("/api/*/bbs/**", "/api/*/members/**")
          .permitAll().anyRequest().authenticated())
          .csrf(csrf -> csrf.disable() // 토큰 검사 비활성화
      );
    return http.build();
  }
}
