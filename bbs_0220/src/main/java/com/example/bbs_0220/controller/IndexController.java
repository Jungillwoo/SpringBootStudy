package com.example.bbs_0220.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
public class IndexController {

    @RequestMapping("/")
    public String requestMethodName() {
        return "index";
    }
    
}
