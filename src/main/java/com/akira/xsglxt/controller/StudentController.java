package com.akira.xsglxt.controller;

import com.akira.xsglxt.common.Result;
import com.akira.xsglxt.entity.Student;
import com.akira.xsglxt.service.StudentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping
    public Result<Page<Student>> list(
            @RequestParam(defaultValue = "1") Long pageNum,
            @RequestParam(defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) Long classId) {
        Page<Student> page = studentService.pageList(pageNum, pageSize, studentName, classId);
        return Result.ok(page);
    }

    @GetMapping("/{id}")
    public Result<Student> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        return Result.ok(student);
    }

    @PostMapping
    public Result<String> add(@RequestBody Student student) {
        boolean success = studentService.add(student);
        return success ? Result.ok("添加成功") : Result.fail("添加失败");
    }

    @PutMapping
    public Result<String> update(@RequestBody Student student) {
        boolean success = studentService.update(student);
        return success ? Result.ok("修改成功") : Result.fail("修改失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean success = studentService.delete(id);
        return success ? Result.ok("删除成功") : Result.fail("删除失败");
    }
}
