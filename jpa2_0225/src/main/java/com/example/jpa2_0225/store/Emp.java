package com.example.jpa2_0225.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "emp")
@Getter
@Setter
public class Emp {
    
    @Id
    @Column(name = "empno")
    private String empno;

    @Column(name = "ename")
    private String ename;

    @Column(name = "job")
    private String job;

    @Column(name = "deptno")
    private String deptno;

    @Column(name = "sal")
    private long sal;
}
