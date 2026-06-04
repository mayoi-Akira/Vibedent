package com.akira.xsglxt.service;

import com.akira.xsglxt.entity.Student;

import java.util.List;

/**
 * 学生服务接口
 */
public interface StudentService {

    /**
     * 获取全部学生列表
     */
    List<Student> listAll();
}
