package com.CatTree.web.controller.system;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.CatTree.common.annotation.Log;
import com.CatTree.common.core.controller.BaseController;
import com.CatTree.common.core.domain.AjaxResult;
import com.CatTree.common.enums.BusinessType;
import com.CatTree.system.domain.SchoolAttendanceDetail;
import com.CatTree.system.service.ISchoolAttendanceDetailService;
import com.CatTree.common.utils.poi.ExcelUtil;
import com.CatTree.common.core.page.TableDataInfo;

/**
 * 考勤明细Controller
 *
 * @author CatTree
 * @date 2022-03-11
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
//    @PreAuthorize("@ss.hasPermi('system:detail:list')")
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
//    @PreAuthorize("@ss.hasPermi('system:detail:export')")
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
//    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(schoolAttendanceDetailService.selectSchoolAttendanceDetailById(id));
    }

    /**
     * 新增考勤明细
     */
//    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "考勤明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolAttendanceDetail schoolAttendanceDetail)
    {
        return toAjax(schoolAttendanceDetailService.insertSchoolAttendanceDetail(schoolAttendanceDetail));
    }

    /**
     * 修改考勤明细
     */
//    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "考勤明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolAttendanceDetail schoolAttendanceDetail)
    {
        return toAjax(schoolAttendanceDetailService.updateSchoolAttendanceDetail(schoolAttendanceDetail));
    }

    /**
     * 删除考勤明细
     */
//    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "考勤明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(schoolAttendanceDetailService.deleteSchoolAttendanceDetailByIds(ids));
    }

    @RequestMapping("/signInOrCancel")
    @ResponseBody
    @Transactional
    public AjaxResult signInOrCancel(@RequestParam(name = "id") Long id,@RequestParam(name = "flag") boolean flag) {

        if(flag){
            schoolAttendanceDetailService.signIn(id);
        }else{
            schoolAttendanceDetailService.cancelSignIn(id);
        }
        return AjaxResult.success();
    }
}
