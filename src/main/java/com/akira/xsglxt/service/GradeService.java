package com.akira.xsglxt.service;

import com.akira.xsglxt.entity.Grade;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 年级服务接口
 */
public interface GradeService {

    /**
     * 分页查询年级列表
     */
    Page<Grade> pageList(Long pageNum, Long pageSize, String gradeName);

    /**
     * 获取全部年级列表
     */
    List<Grade> listAll();

    /**
     * 获取年级详情
     */
    Grade getById(Long id);

    /**
     * 新增年级
     */
    boolean add(Grade grade);

    /**
     * 修改年级
     */
    boolean update(Grade grade);

    /**
     * 删除年级
     */
    boolean delete(Long id);
}
