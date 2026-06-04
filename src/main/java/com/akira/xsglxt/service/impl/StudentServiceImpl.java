package com.akira.xsglxt.service.impl;

import com.akira.xsglxt.entity.Student;
import com.akira.xsglxt.mapper.StudentMapper;
import com.akira.xsglxt.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生服务实现
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Student> listAll() {
        return studentMapper.selectList(null);
    }
}
