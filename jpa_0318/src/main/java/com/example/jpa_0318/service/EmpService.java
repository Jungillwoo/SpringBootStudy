package com.example.jpa_0318.service;

import com.example.jpa_0318.repository.EmpRepository;
import com.example.jpa_0318.store.Emp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpService {

  private final EmpRepository empRepository;

  public List<Emp> findAll() {
   return empRepository.findAll();
  }

  public Emp findByEmpno(Long empno) {
    return empRepository.findById(empno).orElse(null);
  }

  public List<Emp> findByJobAndDeptno(String job, Long deptno) {
    return empRepository.findByJobAndDeptno(job, deptno);
  }

  public List<Emp> findByJobLikeAndDeptno(String job, Long deptno) {
    return empRepository.findByJobLikeAndDeptno(job, deptno);
  }

  public List<Emp> findByJobContainingAndDeptno(String job, Long deptno) {
    return empRepository.findByJobContainingAndDeptno(job, deptno);
  }
}
