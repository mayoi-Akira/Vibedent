package com.ruoyi.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 班级表实体
 */
@Data
@TableName("class")
public class ClassInfo {

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 所属年级ID */
    private Long gradeId;

    /** 班级名称 */
    private String className;

    /** 班级编码 */
    private String classCode;

    /** 班主任 */
    private String teacher;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 逻辑删除标识 0未删除 1已删除 */
    @TableLogic
    private Integer deleted;
}
