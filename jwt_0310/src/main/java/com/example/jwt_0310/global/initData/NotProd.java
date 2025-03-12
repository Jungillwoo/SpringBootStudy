package com.example.jwt_0310.global.initData;

import com.example.jwt_0310.domain.bbs.entity.service.BbsService;
import com.example.jwt_0310.domain.member.entity.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
  // 가짜 더미 데이터 (개발중, 테스트 할때 미리 데이터들을 넣어주기 위해 사용)
  @Bean
  CommandLineRunner initData(BbsService bbsService, MemberService memberService) {
    // "1111" 을 암호화 시킨다.
    // String pwd = passwordEncoder.encode("1111");

    return args -> { // 람다 형태 {} 안 내용이 바로 반환
      // 회원 추가
      memberService.join("maru", "마루치", "1111");
      bbsService.createBbs("제목1", "정이루1", "테스트내용입니다1.");
      bbsService.createBbs("제목2", "정이루2", "테스트내용입니다2.");
      bbsService.createBbs("제목3", "정이루3", "테스트내용입니다3.");
      bbsService.createBbs("제목4", "정이루4", "테스트내용입니다4.");
    };
  }
}
