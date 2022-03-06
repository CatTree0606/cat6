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
import com.ruoyi.system.domain.SchoolMajor;
import com.ruoyi.system.service.ISchoolMajorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 专业信息Controller
 *
 * @author ruoyi
 * @date 2022-03-06
 */
@RestController
@RequestMapping("/system/major")
public class SchoolMajorController extends BaseController {
    @Autowired
    private ISchoolMajorService schoolMajorService;

    /**
     * 查询专业信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:major:list')")
    @GetMapping("/list")
    public TableDataInfo list(SchoolMajor schoolMajor) {
        startPage();
        List<SchoolMajor> list = schoolMajorService.selectSchoolMajorList(schoolMajor);
        return getDataTable(list);
    }

    /**
     * 导出专业信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:major:export')")
    @Log(title = "专业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolMajor schoolMajor) {
        List<SchoolMajor> list = schoolMajorService.selectSchoolMajorList(schoolMajor);
        ExcelUtil<SchoolMajor> util = new ExcelUtil<SchoolMajor>(SchoolMajor.class);
        util.exportExcel(response, list, "专业信息数据");
    }

    /**
     * 获取专业信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:major:query')")
    @GetMapping(value = "/{majorId}")
    public AjaxResult getInfo(@PathVariable("majorId") Long majorId) {
        return AjaxResult.success(schoolMajorService.selectSchoolMajorByMajorId(majorId));
    }

    /**
     * 新增专业信息
     */
    @PreAuthorize("@ss.hasPermi('system:major:add')")
    @Log(title = "专业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolMajor schoolMajor) {
        return toAjax(schoolMajorService.insertSchoolMajor(schoolMajor));
    }

    /**
     * 修改专业信息
     */
    @PreAuthorize("@ss.hasPermi('system:major:edit')")
    @Log(title = "专业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolMajor schoolMajor) {
        return toAjax(schoolMajorService.updateSchoolMajor(schoolMajor));
    }

    /**
     * 删除专业信息
     */
    @PreAuthorize("@ss.hasPermi('system:major:remove')")
    @Log(title = "专业信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{majorIds}")
    public AjaxResult remove(@PathVariable Long[] majorIds) {
        return toAjax(schoolMajorService.deleteSchoolMajorByMajorIds(majorIds));
    }
}
