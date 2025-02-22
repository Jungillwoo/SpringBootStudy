package com.example.bbs_0220.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bbs_0220.mapper.CommMapper;
import com.example.bbs_0220.vo.CommVO;

@Service
public class CommService {

    @Autowired
    private CommMapper commMapper;

    public List<CommVO> commList(String b_idx) {
        return commMapper.commList(b_idx);
    }
}
