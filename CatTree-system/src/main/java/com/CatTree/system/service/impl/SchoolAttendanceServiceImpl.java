package com.CatTree.system.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.CatTree.common.core.domain.model.LoginUser;
import com.CatTree.common.utils.DateUtils;
import com.CatTree.common.utils.SecurityUtils;
import com.CatTree.system.domain.*;
import com.CatTree.system.mapper.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CatTree.system.service.ISchoolAttendanceService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * 考勤Service业务层处理
 *
 * @author CatTree
 * @date 2022-03-09
 */
@Service
public class SchoolAttendanceServiceImpl implements ISchoolAttendanceService {
    @Autowired
    private SchoolAttendanceMapper schoolAttendanceMapper;
    @Autowired
    private SchoolAttendanceDetailMapper schoolAttendanceDetailMapper;


    @Autowired
    private SchoolClassMapper schoolClassMapper;

    @Autowired
    private SchoolClassUserMapper schoolClassUserMapper;
    @Autowired
    private SchoolCourseMapper schoolCourseMapper;

    /**
     * 查询考勤
     *
     * @param attendanceId 考勤主键
     * @return 考勤
     */
    @Override
    public SchoolAttendance selectSchoolAttendanceByAttendanceId(Long attendanceId) {
        SchoolCourse schoolCourse = new SchoolCourse();
        schoolCourse.setTeacherUserId(SecurityUtils.getLoginUser().getUserId());
        List<SchoolCourse> schoolCourses = schoolCourseMapper.selectSchoolCourseList(schoolCourse);
        SchoolAttendance schoolAttendance = schoolAttendanceMapper.selectSchoolAttendanceByAttendanceId(attendanceId);
        schoolAttendance.setCourseList(schoolCourses);
        return schoolAttendance;
    }

    /**
     * 查询考勤列表
     *
     * @param schoolAttendance 考勤
     * @return 考勤
     */
    @Override
    public List<SchoolAttendance> selectSchoolAttendanceList(SchoolAttendance schoolAttendance) {
        schoolAttendance.setTeacherUserId(SecurityUtils.getLoginUser().getUserId());
        return schoolAttendanceMapper.selectSchoolAttendanceList(schoolAttendance);
    }

    /**
     * 新增考勤
     *
     * @param schoolAttendance 考勤
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSchoolAttendance(SchoolAttendance schoolAttendance) {
        schoolAttendance.setCreateTime(DateUtils.getNowDate());

        LoginUser loginUser = SecurityUtils.getLoginUser();
        schoolAttendance.setTeacherUserId(loginUser.getUserId());

        //查询老师关联的班级的学生数
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setTeacherUserId(loginUser.getUserId());
        List<SchoolClass> schoolClasses = schoolClassMapper.selectSchoolClassList(schoolClass);
        int a = 0;
        List<SchoolClassUser> schoolClassUsers = Lists.newArrayList();
        ArrayList<SchoolClassUser> collect = new ArrayList<>();
        if (!CollectionUtils.isEmpty(schoolClasses)) {
            for (int i = 0; i < schoolClasses.size(); i++) {
                SchoolClassUser schoolClassUser = new SchoolClassUser();
                schoolClassUser.setClassId(schoolClasses.get(i).getClassId());
                schoolClassUsers = schoolClassUserMapper.selectSchoolClassUserList(schoolClassUser);

                if (!CollectionUtils.isEmpty(schoolClassUsers)) {
                    collect = schoolClassUsers.stream().collect(
                            collectingAndThen(
                                    toCollection(() -> new TreeSet<>(comparingLong(SchoolClassUser::getUserId))), ArrayList::new)
                    );
                    a = collect.size();
                }
            }
        }
        SchoolCourse schoolCourse = schoolCourseMapper.selectSchoolCourseByCourseId(schoolAttendance.getCourseId());
        schoolAttendance.setCourseName(schoolCourse.getCourseName());
        schoolAttendance.setTotalNumber(Long.valueOf(a));
        schoolAttendance.setNoSignIn(Long.valueOf(a));
        schoolAttendance.setSignIn((long) 0);
        int i1 = schoolAttendanceMapper.insertSchoolAttendance(schoolAttendance);


        if (a > 0) {
            collect.forEach(schoolClassUser -> {
                SchoolAttendanceDetail schoolAttendanceDetail = new SchoolAttendanceDetail();
                schoolAttendanceDetail.setUserId(schoolClassUser.getUserId());
                schoolAttendanceDetail.setStatus("未打卡");
                schoolAttendanceDetail.setAttendanceId(schoolAttendance.getAttendanceId());
                schoolAttendanceDetail.setCreateTime(new Date());
                schoolAttendanceDetail.setTeacherUserId(schoolAttendance.getTeacherUserId());
                schoolAttendanceDetailMapper.insertSchoolAttendanceDetail(schoolAttendanceDetail);
            });
        }
        return i1;
    }

    /**
     * 修改考勤
     *
     * @param schoolAttendance 考勤
     * @return 结果
     */
    @Override
    public int updateSchoolAttendance(SchoolAttendance schoolAttendance) {
        return schoolAttendanceMapper.updateSchoolAttendance(schoolAttendance);
    }

    /**
     * 批量删除考勤
     *
     * @param attendanceIds 需要删除的考勤主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSchoolAttendanceByAttendanceIds(Long[] attendanceIds) {
        for (int i = 0; i < attendanceIds.length; i++) {
            SchoolAttendanceDetail schoolAttendanceDetail = new SchoolAttendanceDetail();
            schoolAttendanceDetail.setAttendanceId(attendanceIds[i]);
            List<SchoolAttendanceDetail> schoolAttendanceDetails = schoolAttendanceDetailMapper.selectSchoolAttendanceDetailList(schoolAttendanceDetail);
            if (!CollectionUtils.isEmpty(schoolAttendanceDetails)) {
                List<Long> collect = schoolAttendanceDetails.stream().map(s -> s.getId()).collect(Collectors.toList());
                schoolAttendanceDetailMapper.deleteSchoolAttendanceDetailByIds(collect.toArray(new Long[collect.size()]));
            }
        }
        return schoolAttendanceMapper.deleteSchoolAttendanceByAttendanceIds(attendanceIds);
    }

    /**
     * 删除考勤信息
     *
     * @param attendanceId 考勤主键
     * @return 结果
     */

    @Override
    public int deleteSchoolAttendanceByAttendanceId(Long attendanceId) {
        return schoolAttendanceMapper.deleteSchoolAttendanceByAttendanceId(attendanceId);
    }
}
