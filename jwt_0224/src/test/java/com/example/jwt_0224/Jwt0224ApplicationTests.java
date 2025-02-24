package com.example.jwt_0224;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jwt_0224.jwt.JwtProvider;

import io.jsonwebtoken.security.Keys;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

@SpringBootTest
class Jwt0224ApplicationTests {

	@Value("${custom.jwt.secretKey}")
	private String secretKey;

	@Test
	@DisplayName("시크릿key 체크")
	void loadSecretKey() {
		assertThat(secretKey).isNotNull();
	}

	@Test
	@DisplayName("암호화 알고리즘으로 시크릿key 암호화")
	void genBase64() {
		String encoding = Base64.getEncoder().encodeToString(secretKey.getBytes());

		SecretKey secretKey = Keys.hmacShaKeyFor(encoding.getBytes());

		assertThat(secretKey).isNotNull();
	}

	@Autowired
	private JwtProvider jwtProvider;

	@Test
	@DisplayName("JwtProvider를 통해 SecretKey객체 얻기")
	void testJwtProvider() {
		SecretKey secretKey = jwtProvider.getSecretKey();

		assertThat(secretKey).isNotNull();
	}

	@Test
	@DisplayName("동일한 SecretKey인지 확인")
	void sameSecretKey() {
		SecretKey secretKey1 = jwtProvider.getSecretKey();
		SecretKey secretKey2 = jwtProvider.getSecretKey();

		assertThat(secretKey1 == secretKey2).isTrue(); // 주소값 비교
	}

	@Test
	@DisplayName("access token 발급")
	void tokenTest() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", 1L);
		claims.put("m_name", "이루");
		claims.put("m_email", "zuirune@korea.com");

		String accessToken = jwtProvider.genToken(claims, 60*60*3);
		System.out.println("accessToken = " + accessToken);

		assertThat(accessToken).isNotNull();
	}

	@Test
	@DisplayName("유효한 token인지 (만료여부) 확인")
	void tokenValidTest() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", 1L);
		claims.put("m_name", "이루");
		claims.put("m_email", "zuirune@korea.com");

		String accessToken = jwtProvider.genToken(claims, -1); // -1은 만료상태를 의미
		System.out.println("accessToken = " + accessToken);

		assertThat(jwtProvider.verify(accessToken)).isFalse();
	}

	@Test
	@DisplayName("token에서 사용자정보(claims)를 확인")
	void tokenClaimsTest() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", 101L);
		claims.put("m_name", "파머");
		claims.put("m_email", "parmer@korea.com");

		String accessToken = jwtProvider.genToken(claims, 60*60); 
		System.out.println("accessToken = " + accessToken);

		assertThat(jwtProvider.verify(accessToken)).isTrue();

		// 토큰에 저장되어 있는 정보를 받는다.
		Map<String, Object> map = jwtProvider.getClaims(accessToken);
		System.out.println("Claims = " + map);
	}
}
