package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SchoolClass;
import com.ruoyi.system.service.ISchoolClassService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级信息Controller
 *
 * @author ruoyi
 * @date 2022-03-07
 */
@RestController
@RequestMapping("/system/class")
public class SchoolClassController extends BaseController
{
    @Autowired
    private ISchoolClassService schoolClassService;

    /**
     * 查询班级信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(SchoolClass schoolClass)
    {
        startPage();
        List<SchoolClass> list = schoolClassService.selectSchoolClassList(schoolClass);
        return getDataTable(list);
    }

    /**
     * 导出班级信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:class:export')")
    @Log(title = "班级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolClass schoolClass)
    {
        List<SchoolClass> list = schoolClassService.selectSchoolClassList(schoolClass);
        ExcelUtil<SchoolClass> util = new ExcelUtil<SchoolClass>(SchoolClass.class);
        util.exportExcel(response, list, "班级信息数据");
    }

    /**
     * 获取班级信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:class:query')")
    @GetMapping(value = "/{classId}")
    public AjaxResult getInfo(@PathVariable("classId") Long classId)
    {
        return AjaxResult.success(schoolClassService.selectSchoolClassByClassId(classId));
    }

    /**
     * 新增班级信息
     */
    @PreAuthorize("@ss.hasPermi('system:class:add')")
    @Log(title = "班级信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolClass schoolClass)
    {
        return toAjax(schoolClassService.insertSchoolClass(schoolClass));
    }

    /**
     * 修改班级信息
     */
    @PreAuthorize("@ss.hasPermi('system:class:edit')")
    @Log(title = "班级信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolClass schoolClass)
    {
        return toAjax(schoolClassService.updateSchoolClass(schoolClass));
    }

    /**
     * 删除班级信息
     */
    @PreAuthorize("@ss.hasPermi('system:class:remove')")
    @Log(title = "班级信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{classIds}")
    public AjaxResult remove(@PathVariable Long[] classIds)
    {
        return toAjax(schoolClassService.deleteSchoolClassByClassIds(classIds));
    }
}
