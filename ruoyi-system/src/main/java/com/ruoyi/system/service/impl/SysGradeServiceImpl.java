package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.Grade;
import com.ruoyi.system.mapper.GradeMapper;
import com.ruoyi.system.service.ISysGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 年级 业务层处理
 */
@Service
public class SysGradeServiceImpl implements ISysGradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> selectGradeList(Grade grade) {
        LambdaQueryWrapper<Grade> qw = new LambdaQueryWrapper<>();
        if (grade.getGradeName() != null && !grade.getGradeName().isEmpty()) {
            qw.like(Grade::getGradeName, grade.getGradeName());
        }
        if (grade.getGradeCode() != null && !grade.getGradeCode().isEmpty()) {
            qw.eq(Grade::getGradeCode, grade.getGradeCode());
        }
        qw.orderByAsc(Grade::getId);
        return gradeMapper.selectList(qw);
    }

    @Override
    public List<Grade> selectGradeAll() {
        return gradeMapper.selectList(new LambdaQueryWrapper<Grade>().orderByAsc(Grade::getId));
    }

    @Override
    public Grade selectGradeById(Long id) {
        return gradeMapper.selectById(id);
    }

    @Override
    public int insertGrade(Grade grade) {
        return gradeMapper.insert(grade);
    }

    @Override
    public int updateGrade(Grade grade) {
        return gradeMapper.updateById(grade);
    }

    @Override
    public int deleteGradeByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            count += gradeMapper.deleteById(id);
        }
        return count;
    }
}
