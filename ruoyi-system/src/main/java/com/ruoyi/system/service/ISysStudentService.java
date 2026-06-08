package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.Student;
import java.util.List;

/**
 * 学生 业务层
 */
public interface ISysStudentService {

    /**
     * 根据条件分页查询学生列表
     */
    List<Student> selectStudentList(Student student);

    /**
     * 通过ID查询学生
     */
    Student selectStudentById(Long id);

    /**
     * 新增学生
     */
    int insertStudent(Student student);

    /**
     * 修改学生
     */
    int updateStudent(Student student);

    /**
     * 批量删除学生
     */
    int deleteStudentByIds(Long[] ids);

    /**
     * 校验学号是否唯一
     */
    boolean checkStudentNoUnique(Student student);
}
