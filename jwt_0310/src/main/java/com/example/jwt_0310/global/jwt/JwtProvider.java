package com.example.jwt_0310.global.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtProvider {
  @Value("${custom.jwt.secretKey}")
  private String secretKeyCode;

  private SecretKey secretKey;

  public SecretKey getSecretKey() {
    if (secretKey == null) {
      String encoding = Base64.getEncoder().encodeToString(secretKeyCode.getBytes());
      secretKey = Keys.hmacShaKeyFor(encoding.getBytes());
    }
    return secretKey;
  }

  // 토큰 생성
  public String genToken(Map<String, Object> map, int seconds) {

    long now = new Date().getTime();
    Date accessTokenExpiresIn = new Date(now + seconds * 1000L);

    JwtBuilder jwtBuilder = Jwts.builder()
      .subject("okj")
      .expiration(accessTokenExpiresIn);
    Set<String> keys = map.keySet();
    Iterator<String> it = keys.iterator();
    while (it.hasNext()) {
      String key = it.next(); // map 에 저장된 키
      Object value = map.get(key); // map 에 해당 키로 저장된 값
      jwtBuilder.claim(key, value);
    }
    return jwtBuilder.signWith(getSecretKey()).compact();
  }

  // 토큰이 만료되었는지? 확인 (토큰 검증)
  public boolean verify(String token) {
    boolean value = true;
    try {
      Jwts.parser().verifyWith(getSecretKey())
        .build()
        .parseSignedClaims(token);
    } catch (Exception e) { // 토큰의 인증이 안되면 예외가 발생
      e.printStackTrace();
      value = false;
    }
    return value;
  }

  // 토큰에 담긴 사용자정보(claims)를 반환하기
  public Map<String, Object> getClaims(String token) {
    return Jwts.parser().verifyWith(getSecretKey())
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  // AccessToken 을 반환
  public String getAccessToken(Map<String, Object> map) {
    return genToken(map, 60*5); // 5분
  }

  // RefreshToken 을 반환
  public String getRefreshToken(Map<String, Object> map) {
    return genToken(map, 60&60*24*100); // 100일
  }
}
