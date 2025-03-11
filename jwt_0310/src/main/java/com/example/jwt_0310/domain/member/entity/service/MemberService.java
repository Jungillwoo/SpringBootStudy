package com.example.jwt_0310.domain.member.entity.service;

import com.example.jwt_0310.domain.bbs.entity.repository.MemberRepository;
import com.example.jwt_0310.domain.member.entity.Member;
import com.example.jwt_0310.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final JwtProvider jwtProvider;

  public Member join(String mid, String mname, String mpwd) {
    Member member = Member.builder()
      .mid(mid)
      .mname(mname)
      .mpwd(mpwd)
      .build();

    return this.memberRepository.save(member); // 테이블에 저장
  }

  public Optional<Member> login(String mid){
    return null;
  }

  public Member authAndMakeTokens(String mid, String mpwd) {
    Member member = null;
    String accessToken = null;
    try {
      member = memberRepository.findByMid(mid).orElseThrow(
        () -> new RuntimeException("존재하지 않음")); // RuntimeException 강제 실행
      // 회원 데이터 및 토큰 생성 (토큰에 저장할 회원정보들을 Map에 저장)
      Map<String, Object> map = new HashMap<>();
      map.put("idx", member.getB_idx());
      map.put("mid", member.getMid());
      map.put("mname", member.getMname());
      map.put("mpwd", member.getMpwd());
      map.put("write_date", member.getWrite_date().toString());
      accessToken = jwtProvider.genToken(map, 60*60*3);
      if (accessToken != null) {
        member.setAccessToken(accessToken);
      }
      map.put("access_token", member.getAccessToken());
    } catch (Exception e) {}

    System.out.println("accessToken: " + accessToken); // 출력해서 확인
    return member;
  }

  public Optional<Member> getMemberByMid(String mid) {
    return memberRepository.findByMid(mid);
  }
}
