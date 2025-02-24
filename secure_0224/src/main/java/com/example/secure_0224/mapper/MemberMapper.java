package com.example.secure_0224.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.secure_0224.vo.MemberVO;

@Mapper
public interface MemberMapper {
    int reg(MemberVO vo);
    MemberVO login(String m_id);
}
