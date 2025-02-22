package com.example.ex3_db.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpVO {

    private String empno, ename, job, mgr, hiredate, sal, comm, deptno;
}
