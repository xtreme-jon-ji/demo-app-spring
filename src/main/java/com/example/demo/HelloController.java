package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping
    public String hello() {
        return "Hello Cloud Native World!";
    }

    @GetMapping(path = "/api/v1/hello")
    public HelloWorldResponse helloApi() {
        return new HelloWorldResponse("Hello Cloud Native World, Microservices are cool!");
    }
}