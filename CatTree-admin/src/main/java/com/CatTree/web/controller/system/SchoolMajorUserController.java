package com.CatTree.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.CatTree.common.annotation.Log;
import com.CatTree.common.core.controller.BaseController;
import com.CatTree.common.core.domain.AjaxResult;
import com.CatTree.common.core.page.TableDataInfo;
import com.CatTree.common.enums.BusinessType;
import com.CatTree.common.utils.poi.ExcelUtil;
import com.CatTree.system.domain.SchoolMajorUser;
import com.CatTree.system.service.ISchoolMajorUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 课程专业关联Controller
 *
 * @author ruoyi
 * @date 2022-03-21
 */
@RestController
@RequestMapping("/system/majorUser")
public class SchoolMajorUserController extends BaseController
{
    @Autowired
    private ISchoolMajorUserService schoolMajorUserService;

    /**
     * 查询课程专业关联列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SchoolMajorUser schoolMajorUser)
    {
        startPage();
        List<SchoolMajorUser> list = schoolMajorUserService.selectSchoolMajorUserList(schoolMajorUser);
        return getDataTable(list);
    }

    /**
     * 导出课程专业关联列表
     */
    @Log(title = "课程专业关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SchoolMajorUser schoolMajorUser)
    {
        List<SchoolMajorUser> list = schoolMajorUserService.selectSchoolMajorUserList(schoolMajorUser);
        ExcelUtil<SchoolMajorUser> util = new ExcelUtil<SchoolMajorUser>(SchoolMajorUser.class);
        util.exportExcel(response, list, "课程专业关联数据");
    }

    /**
     * 获取课程专业关联详细信息
     */
    @GetMapping(value = "/{majorId}")
    public AjaxResult getInfo(@PathVariable("majorId") Long majorId)
    {
        return AjaxResult.success(schoolMajorUserService.selectSchoolMajorUserByMajorId(majorId));
    }

    /**
     * 新增课程专业关联
     */
    @Log(title = "课程专业关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SchoolMajorUser schoolMajorUser)
    {
        return toAjax(schoolMajorUserService.insertSchoolMajorUser(schoolMajorUser));
    }

    /**
     * 修改课程专业关联
     */
    @Log(title = "课程专业关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolMajorUser schoolMajorUser)
    {
        return toAjax(schoolMajorUserService.updateSchoolMajorUser(schoolMajorUser));
    }

    /**
     * 删除课程专业关联
     */
    @Log(title = "课程专业关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(schoolMajorUserService.deleteSchoolMajorUserByUserIds(userIds));
    }
}
