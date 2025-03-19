package com.example.jpa_0318.repository;

import com.example.jpa_0318.store.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeptRepository extends JpaRepository<Dept, Long> {

  Optional<Dept> findByDeptno(Long deptno);
}
