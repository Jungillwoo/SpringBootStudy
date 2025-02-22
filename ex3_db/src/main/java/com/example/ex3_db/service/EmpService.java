package com.example.ex3_db.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex3_db.mapper.EmpMapper;
import com.example.ex3_db.vo.EmpVO;

@Service
public class EmpService {

    // 필요한 mapper 지정
    @Autowired
    private EmpMapper mapper;

    // 컨트롤러(요청) 에서 호출하는 메서드 정의
    public EmpVO[] getList() {
        EmpVO[] ar = null;
        List<EmpVO> list = mapper.list();
        if (list != null && !list.isEmpty()) {
            // 리스트가 비어있지 않을 때만 배열 생성
            ar = new EmpVO[list.size()];

            // 비어있는 배열에 리스트의 요소들로 복사하기
            list.toArray(ar);
        }
        return ar;
    }

    public EmpVO[] getSearchEmp(int type, String value) {
        EmpVO[] ar = null;

        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("value", value);
        List<EmpVO> list = mapper.searchEmp(map);

        if (list != null && !list.isEmpty()) {
            // 리스트가 비어있지 않을 때만 배열 생성
            ar = new EmpVO[list.size()];

            // 비어있는 배열에 리스트의 요소들로 복사하기
            list.toArray(ar);
        }
        return ar;
    }
}
