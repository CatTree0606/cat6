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
import com.CatTree.system.domain.SchoolClassUser;
import com.CatTree.system.service.ISchoolClassUserService;
import com.CatTree.common.utils.poi.ExcelUtil;
import com.CatTree.common.core.page.TableDataInfo;

/**
 * 班级学生关联Controller
 *
 * @author CatTree
 * @date 2022-03-07
 */
@RestController
@RequestMapping("/system/classuser")
public class SchoolClassUserController extends BaseController
{
    @Autowired
    private ISchoolClassUserService schoolClassUserService;

    /**
     * 查询班级学生关联列表
     */
//    @PreAuthorize("@ss.hasPermi('system:classuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(SchoolClassUser schoolClassUser)
    {
        startPage();
        List<SchoolClassUser> list = schoolClassUserService.selectSchoolClassUserList(schoolClassUser);
        return getDataTable(list);
    }

    /**
     * 导出班级学生关联列表
     */
//    @PreAuthorize("@ss.hasPermi('system:classuser:export')")
    @Log(title = "班级学生关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolClassUser schoolClassUser)
    {
        List<SchoolClassUser> list = schoolClassUserService.selectSchoolClassUserList(schoolClassUser);
        ExcelUtil<SchoolClassUser> util = new ExcelUtil<SchoolClassUser>(SchoolClassUser.class);
        util.exportExcel(response, list, "班级学生关联数据");
    }

    /**
     * 获取班级学生关联详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:classuser:query')")
    @GetMapping(value = "/{classId}")
    public AjaxResult getInfo(@PathVariable("classId") Long classId)
    {
        return AjaxResult.success(schoolClassUserService.selectSchoolClassUserByClassId(classId));
    }

    /**
     * 新增班级学生关联
     */
//    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "班级学生关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolClassUser schoolClassUser)
    {
        return toAjax(schoolClassUserService.insertSchoolClassUser(schoolClassUser));
    }

    /**
     * 修改班级学生关联
     */
//    @PreAuthorize("@ss.hasPermi('system:classuser:edit')")
    @Log(title = "班级学生关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolClassUser schoolClassUser)
    {
        return toAjax(schoolClassUserService.updateSchoolClassUser(schoolClassUser));
    }

    /**
     * 删除班级学生关联
     */
//    @PreAuthorize("@ss.hasPermi('system:classuser:remove')")
    @Log(title = "班级学生关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{classIds}")
    public AjaxResult remove(@PathVariable Long[] classIds)
    {
        return toAjax(schoolClassUserService.deleteSchoolClassUserByClassIds(classIds));
    }
}
