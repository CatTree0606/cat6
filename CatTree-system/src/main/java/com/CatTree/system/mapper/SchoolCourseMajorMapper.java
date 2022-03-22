package com.CatTree.system.mapper;

import java.util.List;
import com.CatTree.system.domain.SchoolCourseMajor;

/**
 * 课程专业关联Mapper接口
 *
 * @author CatTree
 * @date 2022-03-06
 */
public interface SchoolCourseMajorMapper
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
     * 删除课程专业关联
     *
     * @param courseId 课程专业关联主键
     * @return 结果
     */
    public int deleteSchoolCourseMajorByCourseId(Long courseId);

    /**
     * 批量删除课程专业关联
     *
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolCourseMajorByCourseIds(Long[] courseIds);
}
