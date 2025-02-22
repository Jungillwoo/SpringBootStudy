package com.example.ex4_0220.mapper;

import com.example.ex4_0220.vo.EmpVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {
  // 연결하는 mapper문서의 아이디와 동일한 이름으로
  // 추상메서드 정의
  List<EmpVO> list(String type, String value, int pageSize, int offset);
}
