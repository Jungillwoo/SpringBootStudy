package com.example.jwt_0310.global.security;

import com.example.jwt_0310.domain.member.entity.service.MemberService;
import com.example.jwt_0310.global.result.ResultData;
import com.example.jwt_0310.global.service.RequestService;
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

  private final MemberService memberService;
  private final RequestService requestService;

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
    String accessToken = requestService.getCookie("accessToken");
    if (!accessToken.isBlank()) {
      // accessToken 이 만료시 refreshToken 을 얻어내어 검증 후 다시 accessToken 을 받는다
      if (!memberService.validateToken(accessToken)) {
        String refreshToken = requestService.getCookie("refreshToken");

        ResultData<String> resultData = memberService.refreshAccessToken(refreshToken);

        requestService.setHeaderCookie("accessToken", resultData.getData());
      }

      JwtUser jwtUser = memberService.getUserFromAccessToken(accessToken);

      // 인가 처리
      requestService.setMember(jwtUser);
    }

    filterChain.doFilter(request, response);
  }
}
