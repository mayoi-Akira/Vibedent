package com.akira.xsglxt.mapper;

import com.akira.xsglxt.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生表 Mapper
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}