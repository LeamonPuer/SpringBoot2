package com.SpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lemon
 * @create 2022-12-07-9:57
 */
//@ResponseBody
//@Controller
@RestController
public class Hello {
    @RequestMapping("/hello")
    public String hello(){
        return "hello,SpringBoot2!";
    }
}
