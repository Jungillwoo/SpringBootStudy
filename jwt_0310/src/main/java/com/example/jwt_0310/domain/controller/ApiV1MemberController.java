package com.example.jwt_0310.domain.controller;

import com.example.jwt_0310.domain.member.entity.Member;
import com.example.jwt_0310.domain.member.entity.service.MemberService;
import com.example.jwt_0310.domain.member.input.MemVO;
import com.example.jwt_0310.global.result.ResultData;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ApiV1MemberController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private HttpServletResponse response;

  // @GetMapping("")
  // public List<Member> members() {
  //   List<Member> list = new ArrayList<>();
  //
  //   list.add(new Member("A100", "1111", "이름1"));
  //   list.add(new Member("B200", "2222", "이름2"));
  //   list.add(new Member("C300", "3333", "이름3"));
  //
  //   return list;
  // }

  @PostMapping("/login")
public ResultData<Member> login(@Valid @RequestBody MemVO mvo) {
    int cnt = 0;
    String msg = "fail";
    // jwt 토큰 생성
    Member mem = memberService.authAndMakeTokens(mvo.getMid(), mvo.getMpwd());

    if (mem != null) {
      // response.addHeader("Set-Cookie", mem.getAccessToken());

      ResponseCookie cookie = ResponseCookie.from("accessToken", mem.getAccessToken())
        .path("/") // 특정 도메인에서만 사용
        .sameSite("none") // 쿠키전달 안함
        .httpOnly(true) // 쿠키접근 불허
        .secure(true) // http 요청으로만 쿠키를 주고 받을 수 있게 함 (쿠키가 노출 되더라도 암호화 되어서 안전)
        .build();

      response.addHeader("Set-Cookie", cookie.toString());

      cnt = 1;
      msg = "success";
    }
    return ResultData.of(cnt, msg, mem); // json 전달
  }

  @GetMapping("/{mid}")
  public ResultData<Member> getMember(@PathVariable("mid") String param) {
    // DB 로부터 검색된 결과라고 가정
    return memberService.getMemberByMid(param).map(
      m -> ResultData.of(1, "success", m)).orElseGet(
      () -> ResultData.of(0, "fail", null)
    );
  }

}
