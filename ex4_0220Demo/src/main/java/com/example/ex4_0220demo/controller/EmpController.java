package com.example.ex4_0220demo.controller;

import com.example.ex4_0220demo.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class EmpController {

  @GetMapping("/list")
  public ModelAndView list(){
    ModelAndView mv = new ModelAndView();

    EmpVO vo = new EmpVO();
    vo.setEmpno("1");
    System.out.println(vo.getEmpno());

    mv.setViewName("list");
    return mv;
  }
}
