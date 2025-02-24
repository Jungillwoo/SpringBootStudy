package com.example.jwt_0224.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class JWTController {

    @GetMapping("path")
    public String first() {
        return "JWT연습!";
    }
    
}
