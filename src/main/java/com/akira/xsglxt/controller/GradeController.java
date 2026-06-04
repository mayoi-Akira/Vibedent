package com.akira.xsglxt.controller;

import com.akira.xsglxt.common.Result;
import com.akira.xsglxt.entity.Grade;
import com.akira.xsglxt.service.GradeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Resource
    private GradeService gradeService;

    @GetMapping
    public Result<Page<Grade>> list(
            @RequestParam(defaultValue = "1") Long pageNum,
            @RequestParam(defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String gradeName) {
        Page<Grade> page = gradeService.pageList(pageNum, pageSize, gradeName);
        return Result.ok(page);
    }

    @GetMapping("/{id}")
    public Result<Grade> getById(@PathVariable Long id) {
        Grade grade = gradeService.getById(id);
        return Result.ok(grade);
    }

    @PostMapping
    public Result<String> add(@RequestBody Grade grade) {
        boolean success = gradeService.add(grade);
        return success ? Result.ok("添加成功") : Result.fail("添加失败");
    }

    @PutMapping
    public Result<String> update(@RequestBody Grade grade) {
        boolean success = gradeService.update(grade);
        return success ? Result.ok("修改成功") : Result.fail("修改失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean success = gradeService.delete(id);
        return success ? Result.ok("删除成功") : Result.fail("删除失败");
    }
}
