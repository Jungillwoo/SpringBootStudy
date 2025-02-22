package com.example.ex2;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestController {

    // application.properties 파일에 있는 변수값을 가져온다.
    @Value("${msg}")
    private String msg;

    @RequestMapping(value = "/t1", method=RequestMethod.GET)
    public ModelAndView requestMethodName() {
        ModelAndView mv = new ModelAndView();

        mv.addObject("str", "정일우");
        mv.addObject("msg", msg);
        mv.setViewName("t1"); // t1.jsp

        return mv;
    }
    
}
