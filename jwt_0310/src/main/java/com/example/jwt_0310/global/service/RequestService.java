package com.example.jwt_0310.global.service;

import com.example.jwt_0310.domain.member.entity.Member;
import com.example.jwt_0310.domain.member.entity.service.MemberService;
import com.example.jwt_0310.global.security.JwtUser;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequestScope // 요청이 발생할 때마다 Bean 객체가 생성되어 자동적으로 주입됨
@RequiredArgsConstructor
public class RequestService {

  private final HttpServletRequest request;
  private final HttpServletResponse response;
  private final MemberService memberService;
  private final EntityManager entityManager;
  private Member member;

  // JwtAuthorizationFilter 에 있는 getCookie 가져오기
  public String getCookie(String name) {
    Cookie[] cookies = request.getCookies();

    return Arrays.stream(cookies) // cookies 배열에서 스트림 생성
      .filter(cookie -> cookie.getName().equals(name)) // name 과 같은 이름을 가진 쿠키만 필터링 함
      .findFirst() // 필터링된 결과가 여러 개가 있을 수 있는데 첫번째것만
      .map(Cookie::getValue) // 찾은 쿠키 값 가져온다 Cookie 를 반환하는 getValue 를 참조하고
      .orElse(""); // 필터링된 쿠키가 없다면 공백을 반환한다
  }

  // JwtAuthorizationFilter 에 있는 setHeaderCookie 가져오기
  public void setHeaderCookie(String tokenName, String token) {
    ResponseCookie cookie = ResponseCookie.from(tokenName, token)
      .path("/")
      .sameSite("none")
      .secure(true)
      .httpOnly(true)
      .build();
    response.addHeader("Set-Cookie", cookie.toString());
  }

  // JwtAuthorizationFilter 에 있는 인가처리된 부분 가져오기
  public void setMember(JwtUser jwtUser) {
    SecurityContextHolder.getContext().setAuthentication(jwtUser.getAuthentication());
  }

  // 스프링 Context (Spring SecurityContextHolder:보안컨텍스트) 에서
  public JwtUser getJwtUser() {
    // 보안 컨텍스트를 얻어 그것이 Null 인 경우는 Optional 의 empty 를 반환한다
    return Optional.ofNullable(SecurityContextHolder.getContext())
      .map(context -> context.getAuthentication())
      .filter(authentication -> authentication.getPrincipal() instanceof  JwtUser)
      .map(authentication -> (JwtUser)authentication.getPrincipal())
      .orElse(null);
  }

  private boolean checkLogin() {
    return getJwtUser() != null;
  }

  private boolean isLogout() {
    return !checkLogin(); // 로그인 상태면 false 반환
  }

  // 다음은 현재 인증된 사용자의 Member 를 찾아 반환하는 기능
  public Member getMember() {
    if (!checkLogin()) {
      return null;
    }
    if (member == null) {
      member = entityManager.getReference(Member.class, getJwtUser().getMid());
    }
    // entityManager 는 JPA 기능이며 getReference 함수는 DB 를 로드하는 기능이며
    // 인자가 Member 라는 Entity 로 인해 Member 테이블 인식하여 두번째 인자인
    // mid 로 검색을 해온다.
    return member;
  }
}
