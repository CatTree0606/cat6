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
import com.ruoyi.system.domain.SchoolCourse;
import com.ruoyi.system.service.ISchoolCourseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课程信息Controller
 *
 * @author ruoyi
 * @date 2022-03-06
 */
@RestController
@RequestMapping("/system/course")
public class SchoolCourseController extends BaseController {
    @Autowired
    private ISchoolCourseService schoolCourseService;

    /**
     * 查询课程信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(SchoolCourse schoolCourse) {
        startPage();
        List<SchoolCourse> list = schoolCourseService.selectSchoolCourseList(schoolCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:course:export')")
    @Log(title = "课程信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolCourse schoolCourse) {
        List<SchoolCourse> list = schoolCourseService.selectSchoolCourseList(schoolCourse);
        ExcelUtil<SchoolCourse> util = new ExcelUtil<SchoolCourse>(SchoolCourse.class);
        util.exportExcel(response, list, "课程信息数据");
    }

    /**
     * 获取课程信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:course:query')")
    @GetMapping(value = "/{courseId}")
    public AjaxResult getInfo(@PathVariable("courseId") Long courseId) {
        return AjaxResult.success(schoolCourseService.selectSchoolCourseByCourseId(courseId));
    }

    /**
     * 新增课程信息
     */
    @PreAuthorize("@ss.hasPermi('system:course:add')")
    @Log(title = "课程信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolCourse schoolCourse) {
        return toAjax(schoolCourseService.insertSchoolCourse(schoolCourse));
    }

    /**
     * 修改课程信息
     */
    @PreAuthorize("@ss.hasPermi('system:course:edit')")
    @Log(title = "课程信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolCourse schoolCourse) {
        return toAjax(schoolCourseService.updateSchoolCourse(schoolCourse));
    }

    /**
     * 删除课程信息
     */
    @PreAuthorize("@ss.hasPermi('system:course:remove')")
    @Log(title = "课程信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{courseIds}")
    public AjaxResult remove(@PathVariable Long[] courseIds) {
        return toAjax(schoolCourseService.deleteSchoolCourseByCourseIds(courseIds));
    }
}
