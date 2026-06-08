package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.ClassInfo;
import java.util.List;

/**
 * 班级 业务层
 */
public interface ISysClassInfoService {

    /**
     * 根据条件分页查询班级列表
     */
    List<ClassInfo> selectClassInfoList(ClassInfo classInfo);

    /**
     * 查询所有班级
     */
    List<ClassInfo> selectClassInfoAll();

    /**
     * 通过ID查询班级
     */
    ClassInfo selectClassInfoById(Long id);

    /**
     * 新增班级
     */
    int insertClassInfo(ClassInfo classInfo);

    /**
     * 修改班级
     */
    int updateClassInfo(ClassInfo classInfo);

    /**
     * 批量删除班级
     */
    int deleteClassInfoByIds(Long[] ids);
}
