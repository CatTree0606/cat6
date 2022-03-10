package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SchoolClass;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SchoolClassMapper;
import com.ruoyi.system.mapper.SchoolClassUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SchoolLeaveMapper;
import com.ruoyi.system.domain.SchoolLeave;
import com.ruoyi.system.service.ISchoolLeaveService;
import org.springframework.util.CollectionUtils;

/**
 * 请假信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-07
 */
@Service
public class SchoolLeaveServiceImpl implements ISchoolLeaveService
{
    @Autowired
    private SchoolLeaveMapper schoolLeaveMapper;

    @Autowired
    private SchoolClassMapper schoolClassMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    /**
     * 查询请假信息
     *
     * @param leaveId 请假信息主键
     * @return 请假信息
     */
    @Override
    public SchoolLeave selectSchoolLeaveByLeaveId(Long leaveId)
    {
        return schoolLeaveMapper.selectSchoolLeaveByLeaveId(leaveId);
    }

    /**
     * 查询请假信息列表
     *
     * @param schoolLeave 请假信息
     * @return 请假信息
     */
    @Override
    public List<SchoolLeave> selectSchoolLeaveList(SchoolLeave schoolLeave)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        schoolLeave.setUserId(loginUser.getUserId());

//        List<SysUserRole> sysUserRoles = sysUserRoleMapper.queryByUserId(loginUser.getUserId());
//        if(!CollectionUtils.isEmpty(sysUserRoles)){
//            sysUserRoles.stream().forEach(s -> {
//                s.getRoleId();
//            });
//            List<Long> collect = sysUserRoles.stream().map(s -> s.getRoleId()).collect(Collectors.toList());
//            if (collect.contains(101)) {
//
//            }
//        }
        return schoolLeaveMapper.selectSchoolLeaveList(schoolLeave);
    }

    @Override
    public List<SchoolLeave> selectSchoolLeaveListByTeacher(SchoolLeave schoolLeave) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        schoolLeave.setTeacherUserId(loginUser.getUserId());
        return schoolLeaveMapper.selectSchoolLeaveList(schoolLeave);
    }

    /**
     * 新增请假信息
     *
     * @param schoolLeave 请假信息
     * @return 结果
     */
    @Override
    public int insertSchoolLeave(SchoolLeave schoolLeave)
    {
        schoolLeave.setCreateTime(DateUtils.getNowDate());

        LoginUser loginUser = SecurityUtils.getLoginUser();

        schoolLeave.setUserId(loginUser.getUserId());
        schoolLeave.setUserName(loginUser.getUsername());

        List<SchoolClass> schoolClasses = schoolClassMapper.querySchoolClassListByUserId(schoolLeave.getUserId());
        if(!CollectionUtils.isEmpty(schoolClasses)){
            SchoolClass schoolClass = schoolClasses.get(0);
            schoolLeave.setTeacherUserId(schoolClass.getTeacherUserId());
            schoolLeave.setTeacherUseName(schoolClass.getTeacherUserName());
        }

        return schoolLeaveMapper.insertSchoolLeave(schoolLeave);
    }

    /**
     * 修改请假信息
     *
     * @param schoolLeave 请假信息
     * @return 结果
     */
    @Override
    public int updateSchoolLeave(SchoolLeave schoolLeave)
    {
        schoolLeave.setUpdateTime(DateUtils.getNowDate());
        return schoolLeaveMapper.updateSchoolLeave(schoolLeave);
    }

    /**
     * 批量删除请假信息
     *
     * @param leaveIds 需要删除的请假信息主键
     * @return 结果
     */
    @Override
    public int deleteSchoolLeaveByLeaveIds(Long[] leaveIds)
    {
        return schoolLeaveMapper.deleteSchoolLeaveByLeaveIds(leaveIds);
    }

    /**
     * 删除请假信息信息
     *
     * @param leaveId 请假信息主键
     * @return 结果
     */
    @Override
    public int deleteSchoolLeaveByLeaveId(Long leaveId)
    {
        return schoolLeaveMapper.deleteSchoolLeaveByLeaveId(leaveId);
    }
}
