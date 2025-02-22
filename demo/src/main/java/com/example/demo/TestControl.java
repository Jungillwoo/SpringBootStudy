package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestControl {

    @GetMapping("/tt")
    public String getMethodName(@RequestParam String param) {
        return "반가워요 스프링입니다." + param;
    }

    @GetMapping("test/{var}")
    //public String test(@PathVariable("var") String param) {
    public String test(@PathVariable String var) {
        return "경로변수활용:" + var;
    }

    @GetMapping("/req1")
    public String getReq(String name, String email) {
        return name + "/" + email;
    }
    
       
}