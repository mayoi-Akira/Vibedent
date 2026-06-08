package com.ruoyi.web.controller.business;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.mapper.ClassInfoMapper;
import com.ruoyi.system.mapper.GradeMapper;
import com.ruoyi.system.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页 Dashboard
 */
@RestController
@RequestMapping("/business/dashboard")
public class DashboardController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Autowired
    private GradeMapper gradeMapper;

    /**
     * 获取统计数据
     */
    @GetMapping("/stats")
    public AjaxResult stats() {
        Map<String, Object> data = new HashMap<>();
        data.put("studentCount", studentMapper.selectCount(null));
        data.put("classCount", classInfoMapper.selectCount(null));
        data.put("gradeCount", gradeMapper.selectCount(null));
        return AjaxResult.success(data);
    }
}
