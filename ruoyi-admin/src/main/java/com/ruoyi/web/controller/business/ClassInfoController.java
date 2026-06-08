package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.ClassInfo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ISysClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级管理
 */
@RestController
@RequestMapping("/business/class")
public class ClassInfoController extends BaseController {

    @Autowired
    private ISysClassInfoService classInfoService;

    /**
     * 获取班级列表
     */
    @PreAuthorize("@ss.hasPermi('business:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClassInfo classInfo) {
        startPage();
        List<ClassInfo> list = classInfoService.selectClassInfoList(classInfo);
        return getDataTable(list);
    }

    /**
     * 查询所有班级（下拉选项用）
     */
    @PreAuthorize("@ss.hasPermi('business:class:list')")
    @GetMapping("/all")
    public AjaxResult all() {
        return success(classInfoService.selectClassInfoAll());
    }

    /**
     * 根据ID获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:class:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(classInfoService.selectClassInfoById(id));
    }

    /**
     * 新增班级
     */
    @PreAuthorize("@ss.hasPermi('business:class:add')")
    @Log(title = "班级管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClassInfo classInfo) {
        return toAjax(classInfoService.insertClassInfo(classInfo));
    }

    /**
     * 修改班级
     */
    @PreAuthorize("@ss.hasPermi('business:class:edit')")
    @Log(title = "班级管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClassInfo classInfo) {
        return toAjax(classInfoService.updateClassInfo(classInfo));
    }

    /**
     * 删除班级
     */
    @PreAuthorize("@ss.hasPermi('business:class:remove')")
    @Log(title = "班级管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(classInfoService.deleteClassInfoByIds(ids));
    }
}
