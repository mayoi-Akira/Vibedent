package com.akira.xsglxt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 学生表实体
 */
@Data
@TableName("student")
public class Student {

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 学号 */
    private String studentNo;

    /** 学生姓名 */
    private String studentName;

    /** 性别 1男 2女 */
    private Integer gender;

    /** 年龄 */
    private Integer age;

    /** 班级ID */
    private Long classId;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 逻辑删除标识 0未删除 1已删除 */
    @TableLogic
    private Integer deleted;
}