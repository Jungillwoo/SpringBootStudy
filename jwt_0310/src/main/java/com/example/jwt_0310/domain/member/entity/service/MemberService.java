package com.example.jwt_0310.domain.member.entity.service;

import com.example.jwt_0310.domain.bbs.entity.repository.MemberRepository;
import com.example.jwt_0310.domain.member.entity.Member;
import com.example.jwt_0310.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 반드시 값을 받아야 하는 생성자 자동 생성
public class MemberService {

  // @RequiredArgsConstructor 때문에 @AutoWired 선언 안함
  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;
  private final PasswordEncoder passwordEncoder;

  public Member join(String mid, String mname, String mpwd) {
    String pwd = passwordEncoder.encode(mpwd);
    Member member = Member.builder()
      .mid(mid)
      .mname(mname)
      .mpwd(pwd)
      .build();

    Map<String, Object> map = new HashMap<>();
    map.put("mid", member.getMid());
    map.put("mname", member.getMname());
    map.put("mpwd", member.getMpwd());

    // accessToken, refreshToken 을 모두 DB 에 저장하기 위해 받아낸다.
    String accessToken = jwtProvider.getAccessToken(map);
    member.setAccessToken(accessToken);

    String refreshToken = jwtProvider.getRefreshToken(map);
    member.setRefreshToken(refreshToken);

    return this.memberRepository.save(member); // 테이블에 저장
  }

  public Optional<Member> login(String mid){
    return null;
  }

  public Member authAndMakeTokens(String mid, String mpwd) {
    Member member = null;
    String accessToken = null;

    try {
      // id 만 가지고 회원정보를 얻어낸다
      // 이 안에 암호화된 비밀번호가 존재한다
      member = memberRepository.findByMid(mid).orElseThrow(
        () -> new RuntimeException("존재하지 않음")); // RuntimeException 강제 실행

      // 사용자가 전달해준 비밀번호가 암호화된 비밀번호와 같은지 확인
      // passwordEncoder 통해 확인
      if (passwordEncoder.matches(mpwd, member.getMpwd())) {
        // 회원 데이터 및 토큰 생성 (토큰에 저장할 회원정보들을 Map에 저장)
        Map<String, Object> map = new HashMap<>();
        map.put("idx", member.getB_idx());
        map.put("mid", member.getMid());
        map.put("mname", member.getMname());
        map.put("mpwd", member.getMpwd());
        map.put("write_date", member.getWrite_date().toString());
        accessToken = jwtProvider.getAccessToken(map);
        if (accessToken != null) {
          member.setAccessToken(accessToken);
        }
        map.put("access_token", member.getAccessToken());
      } else {
        member = null;
      }
    } catch (Exception e) {}

    System.out.println("accessToken: " + accessToken); // 출력해서 확인
    return member;
  }

  public Optional<Member> getMemberByMid(String mid) {
    return memberRepository.findByMid(mid);
  }
}
