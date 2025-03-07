package tt.boot_0306.controller;

import org.springframework.web.bind.annotation.*;
import tt.boot_0306.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import tt.boot_0306.vo.MemberVO;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/member")
public class MemberController {

    // DB활용을 위한 service객체
    @Autowired
    private MemberService memberService;

    // 로그인 처리를 위한 session
    @Autowired
    private HttpSession session;

    // @RequestMapping
    // public ModelAndView index() {
    //     ModelAndView mv = new ModelAndView();
    //     mv.setViewName("index");
    //     return mv;
    // }
    //
    // @RequestMapping("/reg")
    // public ModelAndView reg() {
    //     ModelAndView mv = new ModelAndView();
    //     mv.setViewName("reg");
    //     return mv;
    // }
    //
    // @PostMapping("/reg")
    // public ModelAndView reg(MemberVO vo) {
    //     ModelAndView mv = new ModelAndView();
    //
    //     int cnt = memberService.reg(vo);
    //
    //     // 저장 후 기본키가 있는지 검사
    //     System.out.println("멤버기본키:" + vo.getM_idx());
    //
    //     mv.setViewName("index");
    //     return mv;
    // }
    //
    // @GetMapping("/login")
    // public ModelAndView login() {
    //     ModelAndView mv = new ModelAndView();
    //
    //     mv.setViewName("login");
    //
    //     return mv;
    // }

    @PostMapping("/reqLogin")
    @ResponseBody
    public Map<String, Object> login(@RequestBody MemberVO vo) {
      Map<String, Object> response = new HashMap<>();
      MemberVO mvo = memberService.login(vo);

      if (mvo != null) {
        //session.setAttribute("mvo", mvo);
        response.put("status", "0");  // 로그인 성공
        response.put("message", "로그인 성공");
        response.put("user", mvo); // 사용자 정보 반환
      } else {
        response.put("status", "1");  // 로그인 실패
        response.put("message", "아이디 또는 비밀번호가 틀렸습니다.");
      }

      return response;
    }

}
