package com.CatTree.system.mapper;

import java.util.List;
import com.CatTree.system.domain.SchoolCourse;

/**
 * 课程信息Mapper接口
 *
 * @author CatTree
 * @date 2022-03-06
 */
public interface SchoolCourseMapper
{
    /**
     * 查询课程信息
     *
     * @param courseId 课程信息主键
     * @return 课程信息
     */
    public SchoolCourse selectSchoolCourseByCourseId(Long courseId);

    /**
     * 查询课程信息列表
     *
     * @param schoolCourse 课程信息
     * @return 课程信息集合
     */
    public List<SchoolCourse> selectSchoolCourseList(SchoolCourse schoolCourse);

    /**
     * 新增课程信息
     *
     * @param schoolCourse 课程信息
     * @return 结果
     */
    public int insertSchoolCourse(SchoolCourse schoolCourse);

    /**
     * 修改课程信息
     *
     * @param schoolCourse 课程信息
     * @return 结果
     */
    public int updateSchoolCourse(SchoolCourse schoolCourse);

    /**
     * 删除课程信息
     *
     * @param courseId 课程信息主键
     * @return 结果
     */
    public int deleteSchoolCourseByCourseId(Long courseId);

    /**
     * 批量删除课程信息
     *
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolCourseByCourseIds(Long[] courseIds);
}
