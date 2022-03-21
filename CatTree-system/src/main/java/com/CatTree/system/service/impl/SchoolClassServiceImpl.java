package com.CatTree.system.service.impl;

import java.util.List;

import com.CatTree.common.core.domain.entity.SysUser;
import com.CatTree.common.core.domain.model.LoginUser;
import com.CatTree.common.utils.DateUtils;
import com.CatTree.common.utils.SecurityUtils;
import com.CatTree.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CatTree.system.mapper.SchoolClassMapper;
import com.CatTree.system.domain.SchoolClass;
import com.CatTree.system.service.ISchoolClassService;

/**
 * 班级信息Service业务层处理
 *
 * @author CatTree
 * @date 2022-03-07
 */
@Service
public class SchoolClassServiceImpl implements ISchoolClassService
{
    @Autowired
    private SchoolClassMapper schoolClassMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询班级信息
     *
     * @param classId 班级信息主键
     * @return 班级信息
     */
    @Override
    public SchoolClass selectSchoolClassByClassId(Long classId)
    {

        SchoolClass schoolClass = schoolClassMapper.selectSchoolClassByClassId(classId);
        //全部老师
        List<SysUser> sysUsers = sysUserMapper.teacherList(new SysUser());
        schoolClass.setTeacherList(sysUsers);

        return schoolClass;
    }

    /**
     * 查询班级信息列表
     *
     * @param schoolClass 班级信息
     * @return 班级信息
     */
    @Override
    public List<SchoolClass> selectSchoolClassList(SchoolClass schoolClass)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        if (userId != 1) {
            schoolClass.setTeacherUserId(loginUser.getUserId());
        }

        return schoolClassMapper.selectSchoolClassList(schoolClass);
    }

    /**
     * 新增班级信息
     *
     * @param schoolClass 班级信息
     * @return 结果
     */
    @Override
    public int insertSchoolClass(SchoolClass schoolClass)
    {
        SysUser sysUser = sysUserMapper.selectUserById(schoolClass.getTeacherUserId());

        schoolClass.setCreateTime(DateUtils.getNowDate());
        schoolClass.setTeacherUserName(sysUser.getUserName());
        return schoolClassMapper.insertSchoolClass(schoolClass);
    }

    /**
     * 修改班级信息
     *
     * @param schoolClass 班级信息
     * @return 结果
     */
    @Override
    public int updateSchoolClass(SchoolClass schoolClass)
    {
        SysUser sysUser = sysUserMapper.selectUserById(schoolClass.getTeacherUserId());

        schoolClass.setUpdateTime(DateUtils.getNowDate());
        schoolClass.setTeacherUserName(sysUser.getUserName());
        return schoolClassMapper.updateSchoolClass(schoolClass);
    }

    /**
     * 批量删除班级信息
     *
     * @param classIds 需要删除的班级信息主键
     * @return 结果
     */
    @Override
    public int deleteSchoolClassByClassIds(Long[] classIds)
    {
        return schoolClassMapper.deleteSchoolClassByClassIds(classIds);
    }

    /**
     * 删除班级信息信息
     *
     * @param classId 班级信息主键
     * @return 结果
     */
    @Override
    public int deleteSchoolClassByClassId(Long classId)
    {
        return schoolClassMapper.deleteSchoolClassByClassId(classId);
    }
}
