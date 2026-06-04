package com.akira.xsglxt.controller;

import com.akira.xsglxt.common.Result;
import com.akira.xsglxt.entity.Student;
import com.akira.xsglxt.mapper.StudentMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Resource
    private StudentMapper studentMapper;

    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.ok("hello");
    }

    @GetMapping("/api/students")
    public Result<List<Student>> list() {
        List<Student> students = studentMapper.selectList(null);
        return Result.ok(students);
    }
}
