package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.Grade;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ISysGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 年级管理
 */
@RestController
@RequestMapping("/business/grade")
public class GradeController extends BaseController {

    @Autowired
    private ISysGradeService gradeService;

    /**
     * 获取年级列表
     */
    @PreAuthorize("@ss.hasPermi('business:grade:list')")
    @GetMapping("/list")
    public TableDataInfo list(Grade grade) {
        startPage();
        List<Grade> list = gradeService.selectGradeList(grade);
        return getDataTable(list);
    }

    /**
     * 查询所有年级（下拉选项用）
     */
    @PreAuthorize("@ss.hasPermi('business:grade:list')")
    @GetMapping("/all")
    public AjaxResult all() {
        return success(gradeService.selectGradeAll());
    }

    /**
     * 根据ID获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:grade:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(gradeService.selectGradeById(id));
    }

    /**
     * 新增年级
     */
    @PreAuthorize("@ss.hasPermi('business:grade:add')")
    @Log(title = "年级管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Grade grade) {
        return toAjax(gradeService.insertGrade(grade));
    }

    /**
     * 修改年级
     */
    @PreAuthorize("@ss.hasPermi('business:grade:edit')")
    @Log(title = "年级管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Grade grade) {
        return toAjax(gradeService.updateGrade(grade));
    }

    /**
     * 删除年级
     */
    @PreAuthorize("@ss.hasPermi('business:grade:remove')")
    @Log(title = "年级管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(gradeService.deleteGradeByIds(ids));
    }
}
