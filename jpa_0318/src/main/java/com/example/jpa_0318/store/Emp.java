package com.example.jpa_0318.store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "emp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emp {

  @Id
  @Column(name = "empno")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long empno;
  private String ename;
  private String job;
  private LocalDate hiredate;

  @Column(name = "deptno", insertable = false, updatable = false) // 중복 방지
  private Long deptno;

  @ManyToOne // emp 테이블과 dept 테이블은 다대일 관계 이므로
  @JoinColumn(name = "deptno", referencedColumnName = "deptno")
  private Dept dept;
}
