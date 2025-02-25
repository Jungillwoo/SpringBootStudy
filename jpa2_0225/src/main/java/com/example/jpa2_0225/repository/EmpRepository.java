package com.example.jpa2_0225.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa2_0225.store.Emp;
import java.util.List;


@Repository
public interface EmpRepository extends JpaRepository<Emp, String> {

    // 상속받은 findAll()... , findById() 등의 메서드를 사용할 수 있음
    List<Emp> findByEname(String ename); // ename으로 검색
    List<Emp> findByEnameContaining(String ename); // ename에 ename이 포함된 것 검색
    List<Emp> findByEnameLike(String ename); // ename에 ename이 포함된 것 검색
    List<Emp> findByJobContainsAndDeptno(String job, String deptno); // job에 job이 포함되고 deptno가 같은 것 검색

    List<Emp> findBySalLessThan(long sal); // sal이 sal보다 작은 것 검색 (미만)
    List<Emp> findBySalLessThanEqual(long sal); // sal이 sal보다 작거나 같은 것 검색 (이하)
    List<Emp> findBySalGreaterThan(long sal); // sal이 sal보다 큰 것 검색 (초과)
    List<Emp> findBySalGreaterThanEqual(long sal); // sal이 sal보다 크거나 같은 것 검색 (이상)
}
