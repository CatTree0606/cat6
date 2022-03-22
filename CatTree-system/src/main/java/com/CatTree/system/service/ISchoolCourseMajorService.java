package com.CatTree.system.service;

import java.util.List;
import com.CatTree.system.domain.SchoolCourseMajor;

/**
 * 课程专业关联Service接口
 *
 * @author CatTree
 * @date 2022-03-06
 */
public interface ISchoolCourseMajorService
{
    /**
     * 查询课程专业关联
     *
     * @param courseId 课程专业关联主键
     * @return 课程专业关联
     */
    public SchoolCourseMajor selectSchoolCourseMajorByCourseId(Long courseId);

    /**
     * 查询课程专业关联列表
     *
     * @param schoolCourseMajor 课程专业关联
     * @return 课程专业关联集合
     */
    public List<SchoolCourseMajor> selectSchoolCourseMajorList(SchoolCourseMajor schoolCourseMajor);

    /**
     * 新增课程专业关联
     *
     * @param schoolCourseMajor 课程专业关联
     * @return 结果
     */
    public int insertSchoolCourseMajor(SchoolCourseMajor schoolCourseMajor);

    /**
     * 修改课程专业关联
     *
     * @param schoolCourseMajor 课程专业关联
     * @return 结果
     */
    public int updateSchoolCourseMajor(SchoolCourseMajor schoolCourseMajor);

    /**
     * 批量删除课程专业关联
     *
     * @param courseIds 需要删除的课程专业关联主键集合
     * @return 结果
     */
    public int deleteSchoolCourseMajorByCourseIds(Long[] courseIds);

    /**
     * 删除课程专业关联信息
     *
     * @param courseId 课程专业关联主键
     * @return 结果
     */
    public int deleteSchoolCourseMajorByCourseId(Long courseId);
}
