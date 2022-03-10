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
import com.ruoyi.system.domain.SchoolAttendanceDetail;
import com.ruoyi.system.service.ISchoolAttendanceDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考勤明细Controller
 *
 * @author ruoyi
 * @date 2022-03-09
 */
@RestController
@RequestMapping("/system/detail")
public class SchoolAttendanceDetailController extends BaseController
{
    @Autowired
    private ISchoolAttendanceDetailService schoolAttendanceDetailService;

    /**
     * 查询考勤明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(SchoolAttendanceDetail schoolAttendanceDetail)
    {
        startPage();
        List<SchoolAttendanceDetail> list = schoolAttendanceDetailService.selectSchoolAttendanceDetailList(schoolAttendanceDetail);
        return getDataTable(list);
    }

    /**
     * 导出考勤明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "考勤明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolAttendanceDetail schoolAttendanceDetail)
    {
        List<SchoolAttendanceDetail> list = schoolAttendanceDetailService.selectSchoolAttendanceDetailList(schoolAttendanceDetail);
        ExcelUtil<SchoolAttendanceDetail> util = new ExcelUtil<SchoolAttendanceDetail>(SchoolAttendanceDetail.class);
        util.exportExcel(response, list, "考勤明细数据");
    }

    /**
     * 获取考勤明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{attendanceId}")
    public AjaxResult getInfo(@PathVariable("attendanceId") Long attendanceId)
    {
        return AjaxResult.success(schoolAttendanceDetailService.selectSchoolAttendanceDetailByAttendanceId(attendanceId));
    }

    /**
     * 新增考勤明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "考勤明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolAttendanceDetail schoolAttendanceDetail)
    {
        return toAjax(schoolAttendanceDetailService.insertSchoolAttendanceDetail(schoolAttendanceDetail));
    }

    /**
     * 修改考勤明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "考勤明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolAttendanceDetail schoolAttendanceDetail)
    {
        return toAjax(schoolAttendanceDetailService.updateSchoolAttendanceDetail(schoolAttendanceDetail));
    }

    /**
     * 删除考勤明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "考勤明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attendanceIds}")
    public AjaxResult remove(@PathVariable Long[] attendanceIds)
    {
        return toAjax(schoolAttendanceDetailService.deleteSchoolAttendanceDetailByAttendanceIds(attendanceIds));
    }
}
