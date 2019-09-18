package com.jalasoft.webservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/v1.0/hello")
public class HelloController {
    @GetMapping
    public String SayHello(){
        return "HelloGet";
    }
    @PutMapping
    public String PutHello(){
        return "HelloPut";
    }
    @PostMapping
    public String PostHello(){ return "HelloPost"; }
    @DeleteMapping
    public String DeleteHello(){ return "HelloDelete"; }
}
