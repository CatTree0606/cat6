package com.CatTree.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.CatTree.common.core.domain.entity.SysRole;
import com.CatTree.common.core.domain.entity.SysUser;
import com.CatTree.common.core.domain.model.LoginUser;
import com.CatTree.common.utils.DateUtils;
import com.CatTree.common.utils.SecurityUtils;
import com.CatTree.common.utils.StringUtils;
import com.CatTree.system.domain.SchoolAttendance;
import com.CatTree.system.mapper.SchoolAttendanceMapper;
import com.CatTree.system.mapper.SysUserMapper;
import com.CatTree.system.service.ISchoolAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CatTree.system.mapper.SchoolAttendanceDetailMapper;
import com.CatTree.system.domain.SchoolAttendanceDetail;
import com.CatTree.system.service.ISchoolAttendanceDetailService;
import org.springframework.util.CollectionUtils;

/**
 * 考勤明细Service业务层处理
 *
 * @author CatTree
 * @date 2022-03-11
 */
@Service
public class SchoolAttendanceDetailServiceImpl implements ISchoolAttendanceDetailService
{
    @Autowired
    private SchoolAttendanceDetailMapper schoolAttendanceDetailMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SchoolAttendanceMapper schoolAttendanceMapper;
    @Autowired
    private ISchoolAttendanceService schoolAttendanceService;

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

                SchoolAttendance schoolAttendance = schoolAttendanceMapper.selectSchoolAttendanceByAttendanceId(schoolAttendanceDetail1.getAttendanceId());
                schoolAttendanceDetails.get(i).setCourseName(schoolAttendance.getCourseName());

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

    @Override
    public void signIn(Long id) {
        SchoolAttendanceDetail schoolAttendanceDetail = this.selectSchoolAttendanceDetailById(id);
        SchoolAttendance schoolAttendance = schoolAttendanceService.selectSchoolAttendanceByAttendanceId(schoolAttendanceDetail.getAttendanceId());
        Date date = new Date();
        boolean b = schoolAttendance.getCourseStart().getTime() < date.getTime();
        schoolAttendanceDetail.setStatus("已签到");
        schoolAttendanceDetail.setSignTime(date);
        schoolAttendanceDetail.setIsLate(b ? "是" : "否");
        this.updateSchoolAttendanceDetail(schoolAttendanceDetail);

        Long signIn = schoolAttendance.getSignIn();
        signIn = signIn + new Long(1);
        Long noSignIn = schoolAttendance.getNoSignIn();
        noSignIn = noSignIn - new Long(1);
        schoolAttendance.setSignIn(signIn);
        schoolAttendance.setNoSignIn(noSignIn);
        schoolAttendanceService.updateSchoolAttendance(schoolAttendance);
    }

    @Override
    public void cancelSignIn(Long id) {
        SchoolAttendanceDetail schoolAttendanceDetail = this.selectSchoolAttendanceDetailById(id);
        SchoolAttendance schoolAttendance = schoolAttendanceService.selectSchoolAttendanceByAttendanceId(schoolAttendanceDetail.getAttendanceId());
        Date date = new Date();
        boolean b = schoolAttendance.getCourseStart().getTime() < date.getTime();
        schoolAttendanceDetail.setStatus("未签到");
        schoolAttendanceDetail.setSignTime(null);
        schoolAttendanceDetail.setIsLate(null);
        schoolAttendanceDetailMapper.cancelSchoolAttendance(schoolAttendanceDetail);

        Long signIn = schoolAttendance.getSignIn();
        signIn = signIn - new Long(1);
        Long noSignIn = schoolAttendance.getNoSignIn();
        noSignIn = noSignIn + new Long(1);
        schoolAttendance.setSignIn(signIn);
        schoolAttendance.setNoSignIn(noSignIn);
        schoolAttendanceService.updateSchoolAttendance(schoolAttendance);
    }
}
