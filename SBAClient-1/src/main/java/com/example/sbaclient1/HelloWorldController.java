package com.example.sbaclient1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloWorldController {

    private final String greeting;

    public HelloWorldController(
            @Value("${greeting}") String greeting) {
        this.greeting = greeting;
    }

    @GetMapping
    public String helloWorld() {
        return greeting;
    }
}
