package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.Grade;
import java.util.List;

/**
 * 年级 业务层
 */
public interface ISysGradeService {

    /**
     * 根据条件分页查询年级列表
     */
    List<Grade> selectGradeList(Grade grade);

    /**
     * 查询所有年级
     */
    List<Grade> selectGradeAll();

    /**
     * 通过ID查询年级
     */
    Grade selectGradeById(Long id);

    /**
     * 新增年级
     */
    int insertGrade(Grade grade);

    /**
     * 修改年级
     */
    int updateGrade(Grade grade);

    /**
     * 批量删除年级
     */
    int deleteGradeByIds(Long[] ids);
}
