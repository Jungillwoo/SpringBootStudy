package com.example.jwt_0310.domain.controller;

import com.example.jwt_0310.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ApiV1MemberController {

  @GetMapping("")
  public List<Member> members() {
    List<Member> list = new ArrayList<>();

    list.add(new Member("A100", "1111"));
    list.add(new Member("B200", "2222"));
    list.add(new Member("C300", "3333"));

    return list;
  }
}
