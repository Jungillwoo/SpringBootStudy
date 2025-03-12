package com.example.jwt_0310.global.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// JWT 인증을 위한 사용자 정보객체
// 현 클래스는 주로 springframework.security 와 함께 사용되며,
// JWT 토큰을 기반으로 인증된 사용자정보를 저장처리함
// Spring Security 의 인증 체계와 통합하여 보안기능을 강화할 때 사용함
public class JwtUser extends User {

  @Getter
  private String mid; //

  public JwtUser(String mid, String username, String password,
                 Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities); // 부모 객체의 생성자 호출
    // 그래서 사용자 정보를 초기화함
    this.mid = mid;
  }

  // Authentication 객체를 반환하므로 spring security 에서 인증함
  public Authentication getAuthentication() {
    Authentication auth = new UsernamePasswordAuthenticationToken(
      this, this.getPassword(), this.getAuthorities());

      return auth;
  }
}
