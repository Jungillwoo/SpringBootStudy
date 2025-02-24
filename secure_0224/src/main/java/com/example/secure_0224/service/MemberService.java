package com.example.secure_0224.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.secure_0224.mapper.MemberMapper;
import com.example.secure_0224.vo.MemberVO;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원등록
    public int reg(MemberVO vo) {
        // reg.jsp에서 전달되는 m_id, m_pw, m_name이 컨트롤러에서 vo로
        // 받은 것을 그대로 인자로 받아 현재 메서드로 전달
        // 비밀번호를 암호화한다.
        String pw = passwordEncoder.encode(vo.getM_pw());
        vo.setM_pw(pw);
        // vo.setM_pw(passwordEncoder.encode(vo.getM_pw()));

        return memberMapper.reg(vo);
    }

    // 로그인
    public MemberVO login(MemberVO vo) {
        // DB로부터 mv에 있는 m_id를 보내어 해당 MemberVO를 받아서 반환
        // 이때 비밀번호가 일치하는지는 passwordEncoder.matches()로 확인
        MemberVO mvo = memberMapper.login(vo.getM_id());

        // 사용자가 입력한 아이디가 틀리다면 mvo에는 null을 저장한다.
        if (mvo != null) {
            // 여기서는 아이디가 잘 입력되었으니 이제는 비밀번호가 일치하는지 확인
            if (passwordEncoder.matches(vo.getM_pw(), mvo.getM_pw())) {
                return mvo;
            }
            
        }
        // mvo가 null이거나 비밀번호가 일치하지 않을 경우 수행
        return null;
    }
}
