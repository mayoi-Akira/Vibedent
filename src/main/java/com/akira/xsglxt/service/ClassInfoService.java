package com.akira.xsglxt.service;

import com.akira.xsglxt.entity.ClassInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 班级服务接口
 */
public interface ClassInfoService {

    /**
     * 分页查询班级列表
     */
    Page<ClassInfo> pageList(Long pageNum, Long pageSize, String className, Long gradeId);

    /**
     * 获取全部班级列表
     */
    List<ClassInfo> listAll();

    /**
     * 获取班级详情
     */
    ClassInfo getById(Long id);

    /**
     * 新增班级
     */
    boolean add(ClassInfo classInfo);

    /**
     * 修改班级
     */
    boolean update(ClassInfo classInfo);

    /**
     * 删除班级
     */
    boolean delete(Long id);
}
