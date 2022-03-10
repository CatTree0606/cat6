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
import com.ruoyi.system.domain.SchoolAttendance;
import com.ruoyi.system.service.ISchoolAttendanceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考勤Controller
 *
 * @author ruoyi
 * @date 2022-03-09
 */
@RestController
@RequestMapping("/system/attendance")
public class SchoolAttendanceController extends BaseController
{
    @Autowired
    private ISchoolAttendanceService schoolAttendanceService;

    /**
     * 查询考勤列表
     */
//    @PreAuthorize("@ss.hasPermi('system:attendance:list')")
    @GetMapping("/list")
    public TableDataInfo list(SchoolAttendance schoolAttendance)
    {
        startPage();
        List<SchoolAttendance> list = schoolAttendanceService.selectSchoolAttendanceList(schoolAttendance);
        return getDataTable(list);
    }

    /**
     * 导出考勤列表
     */
//    @PreAuthorize("@ss.hasPermi('system:attendance:export')")
    @Log(title = "考勤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolAttendance schoolAttendance)
    {
        List<SchoolAttendance> list = schoolAttendanceService.selectSchoolAttendanceList(schoolAttendance);
        ExcelUtil<SchoolAttendance> util = new ExcelUtil<SchoolAttendance>(SchoolAttendance.class);
        util.exportExcel(response, list, "考勤数据");
    }

    /**
     * 获取考勤详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:attendance:query')")
    @GetMapping(value = "/{attendanceId}")
    public AjaxResult getInfo(@PathVariable("attendanceId") Long attendanceId)
    {
        return AjaxResult.success(schoolAttendanceService.selectSchoolAttendanceByAttendanceId(attendanceId));
    }

    /**
     * 新增考勤
     */
//    @PreAuthorize("@ss.hasPermi('system:attendance:add')")
    @Log(title = "考勤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolAttendance schoolAttendance)
    {
        return toAjax(schoolAttendanceService.insertSchoolAttendance(schoolAttendance));
    }

    /**
     * 修改考勤
     */
//    @PreAuthorize("@ss.hasPermi('system:attendance:edit')")
    @Log(title = "考勤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolAttendance schoolAttendance)
    {
        return toAjax(schoolAttendanceService.updateSchoolAttendance(schoolAttendance));
    }

    /**
     * 删除考勤
     */
//    @PreAuthorize("@ss.hasPermi('system:attendance:remove')")
    @Log(title = "考勤", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attendanceIds}")
    public AjaxResult remove(@PathVariable Long[] attendanceIds)
    {
        return toAjax(schoolAttendanceService.deleteSchoolAttendanceByAttendanceIds(attendanceIds));
    }
}
