package com.example.jpa_0318.controller;

import com.example.jpa_0318.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emp")
public class EmpController {

  private final EmpService empService;

  @RequestMapping("/all")
  public Object findAll(){
    return empService.findAll();
  }

  @RequestMapping("/findByEmpno")
  public Object findByEmpno(Long empno){
    return empService.findByEmpno(empno);
  }

  @RequestMapping("/findByJobAndDeptno")
  public Object findByJobAndDeptno(String job, Long deptno){
    return empService.findByJobAndDeptno(job, deptno);
  }

  @RequestMapping("/findByJobLikeAndDeptno")
  public Object findByJobLikeAndDeptno(String job, Long deptno){
    return empService.findByJobLikeAndDeptno(job, deptno);
  }

  @RequestMapping("/findByJobContainingAndDeptno")
  public Object findByJobContainingAndDeptno(String job, Long deptno){
    return empService.findByJobContainingAndDeptno(job, deptno);
  }
}
