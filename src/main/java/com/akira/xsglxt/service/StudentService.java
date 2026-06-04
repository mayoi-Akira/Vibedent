package com.akira.xsglxt.service;

import com.akira.xsglxt.entity.Student;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 学生服务接口
 */
public interface StudentService {

    /**
     * 分页查询学生列表
     */
    Page<Student> pageList(Long pageNum, Long pageSize, String studentName, Long classId);

    /**
     * 获取全部学生列表
     */
    List<Student> listAll();

    /**
     * 获取学生详情
     */
    Student getById(Long id);

    /**
     * 新增学生
     */
    boolean add(Student student);

    /**
     * 修改学生
     */
    boolean update(Student student);

    /**
     * 删除学生
     */
    boolean delete(Long id);
}
