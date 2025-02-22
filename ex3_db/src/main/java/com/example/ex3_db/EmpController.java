package com.example.ex3_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.ex3_db.service.EmpService;
import com.example.ex3_db.vo.EmpVO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class EmpController {

    @Autowired
    private EmpService service;

    @RequestMapping("/list")
    public ModelAndView requestMethodName() {
        ModelAndView mv = new ModelAndView();

        // 뷰페이지에서 표현할 자원을 얻어낸다.
        EmpVO[] ar = service.getList();
        mv.addObject("ar", ar);
        mv.setViewName("list");

        return mv;
    }

    @RequestMapping(value = "empSearch", method=RequestMethod.POST)
    public ModelAndView empSearch(@RequestParam("type") int type, @RequestParam("value") String value) {

        ModelAndView mv = new ModelAndView();

        try {
            EmpVO[] ar = service.getSearchEmp(type, value);
            mv.addObject("ar",ar);
          } catch (Exception e) {
            e.printStackTrace();
          }
          mv.setViewName("list");

        return mv;
    }
    
}
