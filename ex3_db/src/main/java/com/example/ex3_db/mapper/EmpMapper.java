package com.example.ex3_db.mapper;

import java.util.List;
import java.util.Map;

import com.example.ex3_db.vo.EmpVO;

public interface EmpMapper {
    // SQL문을 가진 mapper파일 emp.xml과 연동된다.
    // 그래서 여기에 정의하는 함수들은 emp.xml에 존재하는
    // id명과 동일해야 한다.
    List<EmpVO> list();
    List<EmpVO> searchEmp(Map<String, Object> map);
}
