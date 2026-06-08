package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.Student;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ISysStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生管理
 */
@RestController
@RequestMapping("/business/student")
public class StudentController extends BaseController {

    @Autowired
    private ISysStudentService studentService;

    /**
     * 获取学生列表
     */
    @PreAuthorize("@ss.hasPermi('business:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(Student student) {
        startPage();
        List<Student> list = studentService.selectStudentList(student);
        return getDataTable(list);
    }

    /**
     * 根据ID获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:student:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(studentService.selectStudentById(id));
    }

    /**
     * 新增学生
     */
    @PreAuthorize("@ss.hasPermi('business:student:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Student student) {
        if (!studentService.checkStudentNoUnique(student)) {
            return error("新增学生'" + student.getStudentName() + "'失败，学号已存在");
        }
        return toAjax(studentService.insertStudent(student));
    }

    /**
     * 修改学生
     */
    @PreAuthorize("@ss.hasPermi('business:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Student student) {
        if (!studentService.checkStudentNoUnique(student)) {
            return error("修改学生'" + student.getStudentName() + "'失败，学号已存在");
        }
        return toAjax(studentService.updateStudent(student));
    }

    /**
     * 删除学生
     */
    @PreAuthorize("@ss.hasPermi('business:student:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(studentService.deleteStudentByIds(ids));
    }
}
