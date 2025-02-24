package com.example.jwt_0224.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

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

    public String genToken(Map<String, Object> map, int seconds) {
        long now = new Date().getTime();
        Date accessTokenExpiresIn = new Date(now + 1000L*seconds);

        JwtBuilder jwtBuilder = Jwts.builder().subject("2RUU")
            .expiration(accessTokenExpiresIn);
        
        Set<String> keys = map.keySet(); // set 구조의 장점 : 중복을 허용하지 않음, 반복자 처리하기 위해 Set구조화
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) { // 다음 값이 존재할 경우 수행
            String key = it.next();
            Object value = map.get(key);

            jwtBuilder.claim(key, value);
        }
        String jwt = jwtBuilder.signWith(getSecretKey()).compact(); // compact() : 압축된 형태로 변환
        return jwt;
    }

    // 토큰이 만료되었는지 확인
    public boolean verify(String token) {
        boolean value = true;

        try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
        } catch (Exception e) {
            // 유효기간이 만료되면 예외발생함
            value = false;
        }

        return value;
    }

    // 토큰에 담긴 사용자 정보(claims)를 반환한다.
    public Map<String, Object> getClaims(String token) {
        return Jwts.parser().verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}
