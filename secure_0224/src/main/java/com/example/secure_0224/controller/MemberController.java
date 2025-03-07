package com.example.secure_0224.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.secure_0224.service.MemberService;
import com.example.secure_0224.vo.MemberVO;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class MemberController {

    // DB활용을 위한 service객체
    @Autowired
    private MemberService memberService;

    // 로그인 처리를 위한 session
    @Autowired
    private HttpSession session;

    @RequestMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/reg")
    public ModelAndView reg() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reg");
        return mv;
    }

    @PostMapping("/reg")
    public ModelAndView reg(MemberVO vo) {
        ModelAndView mv = new ModelAndView();

        int cnt = memberService.reg(vo);

        // 저장 후 기본키가 있는지 검사
        System.out.println("멤버기본키:" + vo.getM_idx());
        
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login");

        return mv;
    }

    @PostMapping("/reqLogin")
    public ModelAndView login(MemberVO vo) {
        ModelAndView mv = new ModelAndView();

        MemberVO mvo = memberService.login(vo);
        
        // mvo가 null이면 아이디 또는 비밀번호가 틀린경우
        if (mvo != null) {
            session.setAttribute("mvo", mvo);
        } else {
            mv.addObject("status", "1");
        }

        mv.setViewName("redirect:/");

        return mv;
    }

}
