package com.akira.xsglxt.service.impl;

import com.akira.xsglxt.entity.Grade;
import com.akira.xsglxt.mapper.GradeMapper;
import com.akira.xsglxt.service.GradeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 年级服务实现
 */
@Service
public class GradeServiceImpl implements GradeService {

    @Resource
    private GradeMapper gradeMapper;

    @Override
    public Page<Grade> pageList(Long pageNum, Long pageSize, String gradeName) {
        Page<Grade> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Grade> queryWrapper = new LambdaQueryWrapper<>();

        if (gradeName != null && !gradeName.isEmpty()) {
            queryWrapper.like(Grade::getGradeName, gradeName);
        }

        queryWrapper.orderByDesc(Grade::getCreateTime);
        return gradeMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Grade> listAll() {
        return gradeMapper.selectList(null);
    }

    @Override
    public Grade getById(Long id) {
        return gradeMapper.selectById(id);
    }

    @Override
    public boolean add(Grade grade) {
        grade.setCreateTime(LocalDateTime.now());
        grade.setUpdateTime(LocalDateTime.now());
        return gradeMapper.insert(grade) > 0;
    }

    @Override
    public boolean update(Grade grade) {
        grade.setUpdateTime(LocalDateTime.now());
        return gradeMapper.updateById(grade) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return gradeMapper.deleteById(id) > 0;
    }
}
