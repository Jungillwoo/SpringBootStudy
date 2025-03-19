package com.example.jpa_0318.controller;

import com.example.jpa_0318.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DeptController {

  private final DeptService deptService;

  @RequestMapping("/all")
  public Object findAll() {
    return deptService.findAll();
  }

  @RequestMapping("/findByDeptno")
  public Object findByDeptno(Long deptno) {
    return deptService.findByDeptno(deptno);
  }
}
