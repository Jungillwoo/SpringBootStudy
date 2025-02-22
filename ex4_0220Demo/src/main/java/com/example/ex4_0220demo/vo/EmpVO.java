package com.example.ex4_0220demo.vo;

import lombok.*;

@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpVO {
  private String empno, ename, job, mgr, hiredate, sal, comm, deptno;
}
