-- 创建学生管理系统数据库
CREATE DATABASE IF NOT EXISTS xsglxt DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE xsglxt;

-- 年级表
CREATE TABLE IF NOT EXISTS `grade` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `grade_name` varchar(50) NOT NULL COMMENT '年级名称',
  `grade_code` varchar(30) NOT NULL COMMENT '年级编码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint DEFAULT 0 COMMENT '逻辑删除标识 0未删除 1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_grade_code` (`grade_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='年级表';

-- 班级表
CREATE TABLE IF NOT EXISTS `class` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `grade_id` bigint NOT NULL COMMENT '所属年级ID',
  `class_name` varchar(50) NOT NULL COMMENT '班级名称',
  `class_code` varchar(30) NOT NULL COMMENT '班级编码',
  `teacher` varchar(50) COMMENT '班主任',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint DEFAULT 0 COMMENT '逻辑删除标识 0未删除 1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_class_code` (`class_code`),
  KEY `idx_grade_id` (`grade_id`),
  CONSTRAINT `fk_class_grade` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='班级表';

-- 学生表
CREATE TABLE IF NOT EXISTS `student` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_no` varchar(50) NOT NULL COMMENT '学号',
  `student_name` varchar(50) NOT NULL COMMENT '学生姓名',
  `gender` tinyint COMMENT '性别 1男 2女',
  `age` int COMMENT '年龄',
  `class_id` bigint COMMENT '班级ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint DEFAULT 0 COMMENT '逻辑删除标识 0未删除 1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_no` (`student_no`),
  KEY `idx_class_id` (`class_id`),
  CONSTRAINT `fk_student_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生表';

-- 系统用户表
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint DEFAULT 0 COMMENT '逻辑删除标识',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户表';

-- 插入示例数据
INSERT INTO `grade` (`grade_name`, `grade_code`) VALUES
('高一年级', 'G001'),
('高二年级', 'G002'),
('高三年级', 'G003');

INSERT INTO `class` (`grade_id`, `class_name`, `class_code`, `teacher`) VALUES
(1, '高一(1)班', 'C001', '李老师'),
(1, '高一(2)班', 'C002', '王老师'),
(2, '高二(1)班', 'C003', '张老师'),
(2, '高二(2)班', 'C004', '刘老师'),
(3, '高三(1)班', 'C005', '陈老师');

INSERT INTO `student` (`student_no`, `student_name`, `gender`, `age`, `class_id`) VALUES
('S001', '张三', 1, 16, 1),
('S002', '李四', 2, 16, 1),
('S003', '王五', 1, 16, 2),
('S004', '赵六', 2, 16, 2),
('S005', '孙七', 1, 17, 3),
('S006', '周八', 2, 17, 3),
('S007', '吴九', 1, 17, 4),
('S008', '郑十', 2, 17, 4),
('S009', '何十一', 1, 18, 5),
('S010', '韦十二', 2, 18, 5);
