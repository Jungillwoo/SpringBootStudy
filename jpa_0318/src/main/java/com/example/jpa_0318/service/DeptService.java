package com.example.jpa_0318.service;

import com.example.jpa_0318.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeptService {

  private final DeptRepository deptRepository;

  public Object findAll() {
    return deptRepository.findAll();
  }

  public Object findByDeptno(Long deptno) {
    return deptRepository.findByDeptno(deptno);
  }
}
