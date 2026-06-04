package com.akira.xsglxt.service.impl;

import com.akira.xsglxt.entity.Student;
import com.akira.xsglxt.mapper.StudentMapper;
import com.akira.xsglxt.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 学生服务实现
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Page<Student> pageList(Long pageNum, Long pageSize, String studentName, Long classId) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();

        if (studentName != null && !studentName.isEmpty()) {
            queryWrapper.like(Student::getStudentName, studentName);
        }
        if (classId != null && classId > 0) {
            queryWrapper.eq(Student::getClassId, classId);
        }

        queryWrapper.orderByDesc(Student::getCreateTime);
        return studentMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Student> listAll() {
        return studentMapper.selectList(null);
    }

    @Override
    public Student getById(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public boolean add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        return studentMapper.insert(student) > 0;
    }

    @Override
    public boolean update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        return studentMapper.updateById(student) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return studentMapper.deleteById(id) > 0;
    }
}
