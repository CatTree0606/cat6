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
import com.ruoyi.system.domain.SchoolLeave;
import com.ruoyi.system.service.ISchoolLeaveService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 请假信息Controller
 *
 * @author ruoyi
 * @date 2022-03-07
 */
@RestController
@RequestMapping("/system/leave")
public class SchoolLeaveController extends BaseController
{
    @Autowired
    private ISchoolLeaveService schoolLeaveService;

    /**
     * 查询请假信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:leave:list')")
    @GetMapping("/list")
    public TableDataInfo list(SchoolLeave schoolLeave)
    {
        startPage();
        List<SchoolLeave> list = schoolLeaveService.selectSchoolLeaveList(schoolLeave);
        return getDataTable(list);
    }

    @GetMapping("/listAudit")
    public TableDataInfo listAudit(SchoolLeave schoolLeave)
    {
        startPage();
        List<SchoolLeave> list = schoolLeaveService.selectSchoolLeaveListByTeacher(schoolLeave);
        return getDataTable(list);
    }

    /**
     * 导出请假信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:leave:export')")
    @Log(title = "请假信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolLeave schoolLeave)
    {
        List<SchoolLeave> list = schoolLeaveService.selectSchoolLeaveList(schoolLeave);
        ExcelUtil<SchoolLeave> util = new ExcelUtil<SchoolLeave>(SchoolLeave.class);
        util.exportExcel(response, list, "请假信息数据");
    }

    /**
     * 获取请假信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:leave:query')")
    @GetMapping(value = "/{leaveId}")
    public AjaxResult getInfo(@PathVariable("leaveId") Long leaveId)
    {
        return AjaxResult.success(schoolLeaveService.selectSchoolLeaveByLeaveId(leaveId));
    }

    /**
     * 新增请假信息
     */
//    @PreAuthorize("@ss.hasPermi('system:leave:add')")
    @Log(title = "请假信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolLeave schoolLeave)
    {
        return toAjax(schoolLeaveService.insertSchoolLeave(schoolLeave));
    }

    /**
     * 修改请假信息
     */
//    @PreAuthorize("@ss.hasPermi('system:leave:edit')")
    @Log(title = "请假信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolLeave schoolLeave)
    {
        return toAjax(schoolLeaveService.updateSchoolLeave(schoolLeave));
    }

    /**
     * 删除请假信息
     */
//    @PreAuthorize("@ss.hasPermi('system:leave:remove')")
    @Log(title = "请假信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{leaveIds}")
    public AjaxResult remove(@PathVariable Long[] leaveIds)
    {
        return toAjax(schoolLeaveService.deleteSchoolLeaveByLeaveIds(leaveIds));
    }
}
