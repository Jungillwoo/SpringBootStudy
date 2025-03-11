package com.example.jwt_0310.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  @Override
  @SneakyThrows // try ~ catch 로 예외처리를 해야 할 것을 명시적 예외처리를 생략할 수 있도록 한다.
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    if (request.getRequestURI().equals("/api/v1/members/login") ||
      request.getRequestURI().equals("/api/v1/members/logout")) {
      filterChain.doFilter(request, response);
      return;
    } // 로그인/로그아웃은 통과

    // accessToken 검증 또는 refreshToken 발급
    String accessToken = "";
    if (!accessToken.isBlank()) {
    }

    filterChain.doFilter(request, response);
  }
}
