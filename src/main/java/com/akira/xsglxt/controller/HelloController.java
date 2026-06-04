package com.akira.xsglxt.controller;

import com.akira.xsglxt.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.ok("hello");
    }
}

