package com.example.jwt_0310.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApiSecurityConfig {

  // final 로 지정해야 RequiredArgsConstructor 에 의해 자동 대입이 된다.
  private final JwtAuthorizationFilter jwtAuthorizationFilter;

  @Bean
  public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception{
    http.csrf(AbstractHttpConfigurer::disable)
      .headers(headers -> headers.frameOptions(
        HeadersConfigurer.FrameOptionsConfig::sameOrigin))
      .securityMatcher("/api/**") // 설정된 경로로 들어오는 모든 것들 검사
      .authorizeHttpRequests( // 요청에 대한 권한을 지정
        authorize -> authorize
          .requestMatchers("/api/*/bbs/**", "/api/*/members/**").permitAll()
          .requestMatchers(HttpMethod.POST, "/api/*/members/login").permitAll()
          .requestMatchers(HttpMethod.POST, "/api/*/members/logout").permitAll()
          .anyRequest().authenticated()
          )
          .csrf(csrf -> csrf.disable() // 토큰 검사 비활성화
          ) // 요청 시 항상 토큰을 태워서 요청해야 한다.
          .httpBasic(
            httpBasic -> httpBasic.disable()
          ) // httpBasic 로그인 방식 끄기
          .formLogin(
            formLogin -> formLogin.disable()
          ) // 폼 로그인 방식 끄기
          .sessionManagement(
            sessionManagement ->
              sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          ) // 세션 끄기
          .addFilterBefore(
            jwtAuthorizationFilter, // 엑세스토큰을 이용한 로그인 처리
            UsernamePasswordAuthenticationFilter.class
          );
    return http.build();
  }
}
