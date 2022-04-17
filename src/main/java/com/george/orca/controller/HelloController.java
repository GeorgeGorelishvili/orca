package com.george.orca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "hello")
    public String hello() {
        return "Hello Rego";
    }

    @GetMapping(value = "/")
    public String hi() {
        return "HI REGO";
    }
}
