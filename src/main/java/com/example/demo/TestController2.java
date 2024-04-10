package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test2")
public class TestController2 {
    @GetMapping(value="/hello")
    public String test() {
        return "Hello world";
    }
    @GetMapping(value="/bye")
    public String testik() {
        return "Bye world";
    }
}
