package com.CatTree.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.CatTree.common.core.domain.entity.SysUser;
import com.CatTree.common.core.domain.model.LoginUser;
import com.CatTree.common.utils.DateUtils;
import com.CatTree.common.utils.SecurityUtils;
import com.CatTree.system.domain.SchoolCourseMajor;
import com.CatTree.system.domain.SchoolMajor;
import com.CatTree.system.mapper.SchoolCourseMajorMapper;
import com.CatTree.system.mapper.SchoolMajorMapper;
import com.CatTree.system.mapper.SysUserMapper;
import com.CatTree.system.service.ISysUserService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CatTree.system.mapper.SchoolCourseMapper;
import com.CatTree.system.domain.SchoolCourse;
import com.CatTree.system.service.ISchoolCourseService;
import org.springframework.util.CollectionUtils;

/**
 * 课程信息Service业务层处理
 *
 * @author CatTree
 * @date 2022-03-06
 */
@Service
public class SchoolCourseServiceImpl implements ISchoolCourseService {
    @Autowired
    private SchoolCourseMapper schoolCourseMapper;
    @Autowired
    private SchoolCourseMajorMapper schoolCourseMajorMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SchoolMajorMapper schoolMajorMapper;
    @Autowired
    private ISysUserService userService;

    /**
     * 查询课程信息
     *
     * @param courseId 课程信息主键
     * @return 课程信息
     */
    @Override
    public SchoolCourse selectSchoolCourseByCourseId(Long courseId) {
        SchoolCourse schoolCourse = schoolCourseMapper.selectSchoolCourseByCourseId(courseId);
        if (null != schoolCourse) {

            //全部专业
            List<SchoolMajor> schoolMajors = schoolMajorMapper.selectSchoolMajorList(new SchoolMajor());
            schoolCourse.setMajorList(schoolMajors);
            //全部老师
            List<SysUser> sysUsers = sysUserMapper.teacherList(new SysUser());
            schoolCourse.setTeacherList(sysUsers);


            List<SchoolCourseMajor> schoolCourseMajors = schoolCourseMajorMapper.selectSchoolCourseMajorByCourseId(courseId);
            if (!CollectionUtils.isEmpty(schoolCourseMajors)) {
                List<SchoolMajor> list = Lists.newArrayList();
                for (int i = 0; i < schoolCourseMajors.size(); i++) {
                    SchoolMajor schoolMajor = schoolMajorMapper.selectSchoolMajorByMajorId(schoolCourseMajors.get(i).getMajorId());
                    list.add(schoolMajor);
                }
                List<Long> collect = list.stream().map(SchoolMajor -> SchoolMajor.getMajorId()).collect(Collectors.toList());
                //选中的专业
                schoolCourse.setMajorId(collect.toArray(new Long[collect.size()]));
            }
            //选中的老师
            SysUser sysUser = userService.selectUserById(schoolCourse.getTeacherUserId());
            if (null != sysUser) {
                schoolCourse.setTeacherUserId(sysUser.getUserId());
                schoolCourse.setTeacherName(sysUser.getUserName());
            }


        }

        return schoolCourse;
    }

    /**
     * 查询课程信息列表
     *
     * @param schoolCourse 课程信息
     * @return 课程信息
     */
    @Override
    public List<SchoolCourse> selectSchoolCourseList(SchoolCourse schoolCourse) {

        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        if (userId != 1) {
            schoolCourse.setTeacherUserId(loginUser.getUserId());
        }

        List<SchoolCourse> schoolCourses = schoolCourseMapper.selectSchoolCourseList(schoolCourse);
        if (!CollectionUtils.isEmpty(schoolCourses)) {
            for (int i = 0; i < schoolCourses.size(); i++) {
                SysUser sysUser = sysUserMapper.selectUserById(schoolCourses.get(i).getTeacherUserId());
                if (null != sysUser) {

                    schoolCourses.get(i).setTeacherName(sysUser.getUserName());
                }
            }
        }
        return schoolCourses;
    }

    /**
     * 新增课程信息
     *
     * @param schoolCourse 课程信息
     * @return 结果
     */
    @Override
    public int insertSchoolCourse(SchoolCourse schoolCourse) {
        schoolCourse.setCreateTime(DateUtils.getNowDate());
        int i1 = schoolCourseMapper.insertSchoolCourse(schoolCourse);

        addCourseMajor(schoolCourse);
        return i1;
    }

    private void addCourseMajor(SchoolCourse schoolCourse) {
        Long[] majorId = schoolCourse.getMajorId();
        for (int i = 0; i < majorId.length; i++) {
            SchoolCourseMajor schoolMajor = new SchoolCourseMajor();
            schoolMajor.setMajorId(majorId[i]);
            schoolMajor.setCourseId(schoolCourse.getCourseId());
            schoolCourseMajorMapper.insertSchoolCourseMajor(schoolMajor);
        }
    }

    /**
     * 修改课程信息
     *
     * @param schoolCourse 课程信息
     * @return 结果
     */
    @Override
    public int updateSchoolCourse(SchoolCourse schoolCourse) {

        schoolCourseMajorMapper.deleteSchoolCourseMajorByCourseId(schoolCourse.getCourseId());

        addCourseMajor(schoolCourse);

        schoolCourse.setUpdateTime(DateUtils.getNowDate());
        return schoolCourseMapper.updateSchoolCourse(schoolCourse);
    }

    /**
     * 批量删除课程信息
     *
     * @param courseIds 需要删除的课程信息主键
     * @return 结果
     */
    @Override
    public int deleteSchoolCourseByCourseIds(Long[] courseIds) {
        return schoolCourseMapper.deleteSchoolCourseByCourseIds(courseIds);
    }

    /**
     * 删除课程信息信息
     *
     * @param courseId 课程信息主键
     * @return 结果
     */
    @Override
    public int deleteSchoolCourseByCourseId(Long courseId) {
        return schoolCourseMapper.deleteSchoolCourseByCourseId(courseId);
    }
}
