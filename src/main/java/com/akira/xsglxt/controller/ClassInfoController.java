package com.akira.xsglxt.controller;

import com.akira.xsglxt.common.Result;
import com.akira.xsglxt.entity.ClassInfo;
import com.akira.xsglxt.service.ClassInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes")
public class ClassInfoController {

    @Resource
    private ClassInfoService classInfoService;

    @GetMapping
    public Result<Page<ClassInfo>> list(
            @RequestParam(defaultValue = "1") Long pageNum,
            @RequestParam(defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String className,
            @RequestParam(required = false) Long gradeId) {
        Page<ClassInfo> page = classInfoService.pageList(pageNum, pageSize, className, gradeId);
        return Result.ok(page);
    }

    @GetMapping("/{id}")
    public Result<ClassInfo> getById(@PathVariable Long id) {
        ClassInfo classInfo = classInfoService.getById(id);
        return Result.ok(classInfo);
    }

    @PostMapping
    public Result<String> add(@RequestBody ClassInfo classInfo) {
        boolean success = classInfoService.add(classInfo);
        return success ? Result.ok("添加成功") : Result.fail("添加失败");
    }

    @PutMapping
    public Result<String> update(@RequestBody ClassInfo classInfo) {
        boolean success = classInfoService.update(classInfo);
        return success ? Result.ok("修改成功") : Result.fail("修改失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        boolean success = classInfoService.delete(id);
        return success ? Result.ok("删除成功") : Result.fail("删除失败");
    }
}
