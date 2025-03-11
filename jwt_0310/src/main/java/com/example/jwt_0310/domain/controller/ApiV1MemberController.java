package com.example.jwt_0310.domain.controller;

import com.example.jwt_0310.domain.member.entity.Member;
import com.example.jwt_0310.domain.member.entity.service.MemberService;
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
public ResultData<Member> login(@Valid @RequestBody Member member) {
    int cnt = 0;
    String msg = "fail";
    // jwt 토큰 생성
    Member mem = memberService.authAndMakeTokens(member.getMid(), member.getMpwd());

    // response.addHeader("Set-Cookie", mem.getAccessToken());

    ResponseCookie cookie = ResponseCookie.from("accessToken", mem.getAccessToken())
      .path("/")
      .sameSite("none")
      .httpOnly(true)
      .secure(true)
      .build();

    response.addHeader("Set-Cookie", cookie.toString());

    if (mem != null) {
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
