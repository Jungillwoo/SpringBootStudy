package com.example.jwt_0310.domain.controller;

import com.example.jwt_0310.domain.bbs.entity.Bbs;
import com.example.jwt_0310.domain.bbs.entity.service.BbsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bbs")
public class ApiV1BbsController {

  private final BbsService bbsService;

  @GetMapping("")
  public List<Bbs> getList() {
    return bbsService.getList();
  }

  // 기본키를 인자로 받아서 Bbs객체를 검색해서 반환하는 기능
  @GetMapping("/{b_idx}")
  public Bbs getBbs(@PathVariable("b_idx") Long b_idx) {
    return bbsService.getBbs(b_idx);
  }

}
