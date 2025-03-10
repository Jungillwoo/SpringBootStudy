package com.example.jwt_0310.domain.controller;

import com.example.jwt_0310.domain.bbs.entity.Bbs;
import com.example.jwt_0310.domain.bbs.entity.service.BbsService;
import com.example.jwt_0310.global.result.ResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bbs")
public class ApiV1BbsController {

  private final BbsService bbsService;

  @GetMapping("")
  public ResultData<List<Bbs>> getList() {
    List<Bbs> list = bbsService.getList();
    String msg = "fail";
    if (list != null && list.size() > 0) {
      msg = "success";
    }
    return ResultData.of(list.size(), msg, list);
  }

  // 기본키를 인자로 받아서 Bbs객체를 검색해서 반환하는 기능
  @GetMapping("/{b_idx}")
  public ResultData<Bbs> getBbs(@PathVariable("b_idx") Long b_idx) {
    // Bbs bbs = null;
    // String msg = "fail";
    // int totalcount = 0;
    // Optional<Bbs> opt = bbsService.getBbs(b_idx);
    // if (opt.isPresent()) { // 현재 Optional<Bbs> 객체가 값을 가지고 있다면
    //   bbs = opt.get();
    //   msg = "success";
    //   totalcount = 1;
    // }
    // return ResultData.of(totalcount, msg, bbs);

    // getBbs 가 반환하는 값 Optional 이 비어있지 않다면
    // map 안에 정의된 ResultData 를 반환한다.
    return bbsService.getBbs(b_idx).map(
      // Optional 안의 내용을 bbs 변수에 넣어주고  bbs -> ResultData.of() 반환
      bbs -> ResultData.of(1, "success", bbs)
    ).orElseGet(
      // Optional 안의 내용이 비어있기 때문에 () -> ResultData.of() 반환
      () -> ResultData.of(0, "fail", null)
      );
  }

}
