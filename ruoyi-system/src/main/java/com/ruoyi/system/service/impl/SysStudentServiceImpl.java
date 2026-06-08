package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.Student;
import com.ruoyi.system.mapper.StudentMapper;
import com.ruoyi.system.service.ISysStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生 业务层处理
 */
@Service
public class SysStudentServiceImpl implements ISysStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> selectStudentList(Student student) {
        LambdaQueryWrapper<Student> qw = new LambdaQueryWrapper<>();
        if (student.getStudentName() != null && !student.getStudentName().isEmpty()) {
            qw.like(Student::getStudentName, student.getStudentName());
        }
        if (student.getStudentNo() != null && !student.getStudentNo().isEmpty()) {
            qw.eq(Student::getStudentNo, student.getStudentNo());
        }
        if (student.getGender() != null) {
            qw.eq(Student::getGender, student.getGender());
        }
        if (student.getClassId() != null) {
            qw.eq(Student::getClassId, student.getClassId());
        }
        qw.orderByAsc(Student::getId);
        return studentMapper.selectList(qw);
    }

    @Override
    public Student selectStudentById(Long id) {
        return studentMapper.selectById(id);
    }

    @Override
    public int insertStudent(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateById(student);
    }

    @Override
    public int deleteStudentByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            count += studentMapper.deleteById(id);
        }
        return count;
    }

    @Override
    public boolean checkStudentNoUnique(Student student) {
        LambdaQueryWrapper<Student> qw = new LambdaQueryWrapper<>();
        qw.eq(Student::getStudentNo, student.getStudentNo());
        Student exist = studentMapper.selectOne(qw);
        if (exist == null) {
            return true;
        }
        // 如果是修改操作（传入了ID），且查出的ID与自身相同，则也算唯一
        return student.getId() != null && exist.getId().equals(student.getId());
    }
}
