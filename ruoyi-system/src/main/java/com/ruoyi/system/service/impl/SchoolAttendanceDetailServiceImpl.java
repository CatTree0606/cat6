package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SchoolAttendanceDetailMapper;
import com.ruoyi.system.domain.SchoolAttendanceDetail;
import com.ruoyi.system.service.ISchoolAttendanceDetailService;
import org.springframework.util.CollectionUtils;

/**
 * 考勤明细Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-11
 */
@Service
public class SchoolAttendanceDetailServiceImpl implements ISchoolAttendanceDetailService
{
    @Autowired
    private SchoolAttendanceDetailMapper schoolAttendanceDetailMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询考勤明细
     *
     * @param id 考勤明细主键
     * @return 考勤明细
     */
    @Override
    public SchoolAttendanceDetail selectSchoolAttendanceDetailById(Long id)
    {
        return schoolAttendanceDetailMapper.selectSchoolAttendanceDetailById(id);
    }

    /**
     * 查询考勤明细列表
     *
     * @param schoolAttendanceDetail 考勤明细
     * @return 考勤明细
     */
    @Override
    public List<SchoolAttendanceDetail> selectSchoolAttendanceDetailList(SchoolAttendanceDetail schoolAttendanceDetail)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        List<SysRole> collect = loginUser.getUser().getRoles().stream().filter(s -> s.getRoleId() == 101).collect(Collectors.toList());
        int isTeacher;
        if(CollectionUtils.isEmpty(collect)){
            schoolAttendanceDetail.setUserId(loginUser.getUserId());
            isTeacher = 0;
        }else{
            schoolAttendanceDetail.setTeacherUserId(loginUser.getUserId());
            isTeacher = 1;
        }
        List<SchoolAttendanceDetail> schoolAttendanceDetails = schoolAttendanceDetailMapper.selectSchoolAttendanceDetailList(schoolAttendanceDetail);
        if(!CollectionUtils.isEmpty(schoolAttendanceDetails)){
            for (int i = 0; i < schoolAttendanceDetails.size(); i++) {
                SchoolAttendanceDetail schoolAttendanceDetail1 = schoolAttendanceDetails.get(i);
                SysUser sysUser = sysUserMapper.selectUserById(schoolAttendanceDetail1.getUserId());
                if(null != sysUser){
                    schoolAttendanceDetails.get(i).setUserName(sysUser.getUserName());
                }
                SysUser sysUser1 = sysUserMapper.selectUserById(schoolAttendanceDetail1.getTeacherUserId());
                if(null != sysUser1){
                    schoolAttendanceDetails.get(i).setTeacherUserName(sysUser1.getUserName());
                }
                schoolAttendanceDetails.get(i).setIsTeacher(isTeacher);

            }
        }
        return schoolAttendanceDetails;
    }

    /**
     * 新增考勤明细
     *
     * @param schoolAttendanceDetail 考勤明细
     * @return 结果
     */
    @Override
    public int insertSchoolAttendanceDetail(SchoolAttendanceDetail schoolAttendanceDetail)
    {
        schoolAttendanceDetail.setCreateTime(DateUtils.getNowDate());
        return schoolAttendanceDetailMapper.insertSchoolAttendanceDetail(schoolAttendanceDetail);
    }

    /**
     * 修改考勤明细
     *
     * @param schoolAttendanceDetail 考勤明细
     * @return 结果
     */
    @Override
    public int updateSchoolAttendanceDetail(SchoolAttendanceDetail schoolAttendanceDetail)
    {
        if(StringUtils.isNotBlank(schoolAttendanceDetail.getSignOut())){
            schoolAttendanceDetail.setSignOutTime(new Date());
        }

        return schoolAttendanceDetailMapper.updateSchoolAttendanceDetail(schoolAttendanceDetail);
    }

    /**
     * 批量删除考勤明细
     *
     * @param ids 需要删除的考勤明细主键
     * @return 结果
     */
    @Override
    public int deleteSchoolAttendanceDetailByIds(Long[] ids)
    {
        return schoolAttendanceDetailMapper.deleteSchoolAttendanceDetailByIds(ids);
    }

    /**
     * 删除考勤明细信息
     *
     * @param id 考勤明细主键
     * @return 结果
     */
    @Override
    public int deleteSchoolAttendanceDetailById(Long id)
    {
        return schoolAttendanceDetailMapper.deleteSchoolAttendanceDetailById(id);
    }
}
