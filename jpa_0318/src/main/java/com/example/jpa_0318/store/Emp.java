package com.example.jpa_0318.store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "emp")
@Getter @Setter
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
  private Long deptno;
}
