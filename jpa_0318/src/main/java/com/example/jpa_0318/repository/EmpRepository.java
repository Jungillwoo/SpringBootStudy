package com.example.jpa_0318.repository;

import com.example.jpa_0318.store.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmpRepository extends JpaRepository<Emp,Long> {

  // 사번 검색
  Optional<Emp> findByEmpno(Long empno);

  // 직종과 부서코드를 인자로 받아 검색
  List<Emp> findByJobAndDeptno(String job, Long deptno);

  //
  @Query(value = "select * from emp where job like concat('%', ?1, '%') and deptno =:deptno", nativeQuery = true)
  List<Emp> findByJobLikeAndDeptno(String job, Long deptno);

  List<Emp> findByJobContainingAndDeptno(String job, Long deptno);
}
