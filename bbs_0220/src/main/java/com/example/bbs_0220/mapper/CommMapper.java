package com.example.bbs_0220.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.bbs_0220.vo.CommVO;

@Mapper
public interface CommMapper {

    List<CommVO> commList(String b_idx);
}
