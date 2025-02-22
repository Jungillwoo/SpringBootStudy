package com.example.ex4_0220.controller;

import com.example.ex4_0220.service.EmpService;
import com.example.ex4_0220.vo.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmpController {

  @Autowired
  private EmpService empService;

  @GetMapping("/list")
  public ModelAndView list(){
    ModelAndView mv = new ModelAndView();

    EmpVO[] ar = empService.getList(null, null, 10, 0);
    mv.addObject("ar", ar);
    mv.setViewName("list");
    return mv;
  }

  @PostMapping("/empSearch")
  @ResponseBody
  //public ModelAndView list(@RequestParam String type, @RequestParam String value, String cPage){
  public Map<String, Object> search(@RequestParam String type, @RequestParam String value, String cPage) {
    // ModelAndView mv = new ModelAndView();
    Map<String, Object> map = new HashMap<>();

    EmpVO[] ar = empService.getList(type, value, 10, 0);
    // mv.addObject("ar", ar);
    // mv.setViewName("list");
    // return mv;
    map.put("ar", ar);
    map.put("length", ar.length);

    return map;
  }
}
