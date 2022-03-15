package com.CatTree.web.controller.system;

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
import com.CatTree.common.annotation.Log;
import com.CatTree.common.core.controller.BaseController;
import com.CatTree.common.core.domain.AjaxResult;
import com.CatTree.common.enums.BusinessType;
import com.CatTree.system.domain.SchoolCollege;
import com.CatTree.system.service.ISchoolCollegeService;
import com.CatTree.common.utils.poi.ExcelUtil;
import com.CatTree.common.core.page.TableDataInfo;

/**
 * 院系信息Controller
 *
 * @author CatTree
 * @date 2022-03-06
 */
@RestController
@RequestMapping("/system/college")
public class SchoolCollegeController extends BaseController {
    @Autowired
    private ISchoolCollegeService schoolCollegeService;

    /**
     * 查询院系信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:college:list')")
    @GetMapping("/list")
    public TableDataInfo list(SchoolCollege schoolCollege) {
        startPage();
        List<SchoolCollege> list = schoolCollegeService.selectSchoolCollegeList(schoolCollege);
        return getDataTable(list);
    }

    /**
     * 导出院系信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:college:export')")
    @Log(title = "院系信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolCollege schoolCollege) {
        List<SchoolCollege> list = schoolCollegeService.selectSchoolCollegeList(schoolCollege);
        ExcelUtil<SchoolCollege> util = new ExcelUtil<SchoolCollege>(SchoolCollege.class);
        util.exportExcel(response, list, "院系信息数据");
    }

    /**
     * 获取院系信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:college:query')")
    @GetMapping(value = "/{collegeId}")
    public AjaxResult getInfo(@PathVariable("collegeId") Long collegeId) {
        return AjaxResult.success(schoolCollegeService.selectSchoolCollegeByCollegeId(collegeId));
    }

    /**
     * 新增院系信息
     */
    @PreAuthorize("@ss.hasPermi('system:college:add')")
    @Log(title = "院系信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolCollege schoolCollege) {
        return toAjax(schoolCollegeService.insertSchoolCollege(schoolCollege));
    }

    /**
     * 修改院系信息
     */
    @PreAuthorize("@ss.hasPermi('system:college:edit')")
    @Log(title = "院系信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolCollege schoolCollege) {
        return toAjax(schoolCollegeService.updateSchoolCollege(schoolCollege));
    }

    /**
     * 删除院系信息
     */
    @PreAuthorize("@ss.hasPermi('system:college:remove')")
    @Log(title = "院系信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{collegeIds}")
    public AjaxResult remove(@PathVariable Long[] collegeIds) {
        return toAjax(schoolCollegeService.deleteSchoolCollegeByCollegeIds(collegeIds));
    }
}
