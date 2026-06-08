package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.ClassInfo;
import com.ruoyi.system.mapper.ClassInfoMapper;
import com.ruoyi.system.service.ISysClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班级 业务层处理
 */
@Service
public class SysClassInfoServiceImpl implements ISysClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Override
    public List<ClassInfo> selectClassInfoList(ClassInfo classInfo) {
        LambdaQueryWrapper<ClassInfo> qw = new LambdaQueryWrapper<>();
        if (classInfo.getClassName() != null && !classInfo.getClassName().isEmpty()) {
            qw.like(ClassInfo::getClassName, classInfo.getClassName());
        }
        if (classInfo.getClassCode() != null && !classInfo.getClassCode().isEmpty()) {
            qw.eq(ClassInfo::getClassCode, classInfo.getClassCode());
        }
        if (classInfo.getGradeId() != null) {
            qw.eq(ClassInfo::getGradeId, classInfo.getGradeId());
        }
        qw.orderByAsc(ClassInfo::getId);
        return classInfoMapper.selectList(qw);
    }

    @Override
    public List<ClassInfo> selectClassInfoAll() {
        return classInfoMapper.selectList(new LambdaQueryWrapper<ClassInfo>().orderByAsc(ClassInfo::getId));
    }

    @Override
    public ClassInfo selectClassInfoById(Long id) {
        return classInfoMapper.selectById(id);
    }

    @Override
    public int insertClassInfo(ClassInfo classInfo) {
        return classInfoMapper.insert(classInfo);
    }

    @Override
    public int updateClassInfo(ClassInfo classInfo) {
        return classInfoMapper.updateById(classInfo);
    }

    @Override
    public int deleteClassInfoByIds(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            count += classInfoMapper.deleteById(id);
        }
        return count;
    }
}
