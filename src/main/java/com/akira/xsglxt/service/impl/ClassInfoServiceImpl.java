package com.akira.xsglxt.service.impl;

import com.akira.xsglxt.entity.ClassInfo;
import com.akira.xsglxt.mapper.ClassInfoMapper;
import com.akira.xsglxt.service.ClassInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 班级服务实现
 */
@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Resource
    private ClassInfoMapper classInfoMapper;

    @Override
    public Page<ClassInfo> pageList(Long pageNum, Long pageSize, String className, Long gradeId) {
        Page<ClassInfo> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<ClassInfo> queryWrapper = new LambdaQueryWrapper<>();

        if (className != null && !className.isEmpty()) {
            queryWrapper.like(ClassInfo::getClassName, className);
        }
        if (gradeId != null && gradeId > 0) {
            queryWrapper.eq(ClassInfo::getGradeId, gradeId);
        }

        queryWrapper.orderByDesc(ClassInfo::getCreateTime);
        return classInfoMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<ClassInfo> listAll() {
        return classInfoMapper.selectList(null);
    }

    @Override
    public ClassInfo getById(Long id) {
        return classInfoMapper.selectById(id);
    }

    @Override
    public boolean add(ClassInfo classInfo) {
        classInfo.setCreateTime(LocalDateTime.now());
        classInfo.setUpdateTime(LocalDateTime.now());
        return classInfoMapper.insert(classInfo) > 0;
    }

    @Override
    public boolean update(ClassInfo classInfo) {
        classInfo.setUpdateTime(LocalDateTime.now());
        return classInfoMapper.updateById(classInfo) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return classInfoMapper.deleteById(id) > 0;
    }
}
