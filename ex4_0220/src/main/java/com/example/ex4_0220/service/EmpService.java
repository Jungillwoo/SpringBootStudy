package com.example.ex4_0220.service;

import com.example.ex4_0220.mapper.EmpMapper;
import com.example.ex4_0220.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

  @Autowired
  private EmpMapper empMapper;

  public EmpVO[] getList(String type, String value, int pageSize, int offset) {
    EmpVO[] ar = null;

    System.out.println("type:" + type + ",value:" + value);

    List<EmpVO> list = empMapper.list(type, value, pageSize, offset);
    if (list != null && list.size() > 0) {
      ar = new EmpVO[list.size()];
      list.toArray(ar);
    }
    return ar;
  }
}
