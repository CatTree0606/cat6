package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SchoolCourseMajorMapper;
import com.ruoyi.system.domain.SchoolCourseMajor;
import com.ruoyi.system.service.ISchoolCourseMajorService;

/**
 * 课程专业关联Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-06
 */
@Service
public class SchoolCourseMajorServiceImpl implements ISchoolCourseMajorService
{
    @Autowired
    private SchoolCourseMajorMapper schoolCourseMajorMapper;

    /**
     * 查询课程专业关联
     *
     * @param courseId 课程专业关联主键
     * @return 课程专业关联
     */
    @Override
    public List<SchoolCourseMajor> selectSchoolCourseMajorByCourseId(Long courseId)
    {
        return schoolCourseMajorMapper.selectSchoolCourseMajorByCourseId(courseId);
    }

    /**
     * 查询课程专业关联列表
     *
     * @param schoolCourseMajor 课程专业关联
     * @return 课程专业关联
     */
    @Override
    public List<SchoolCourseMajor> selectSchoolCourseMajorList(SchoolCourseMajor schoolCourseMajor)
    {
        return schoolCourseMajorMapper.selectSchoolCourseMajorList(schoolCourseMajor);
    }

    /**
     * 新增课程专业关联
     *
     * @param schoolCourseMajor 课程专业关联
     * @return 结果
     */
    @Override
    public int insertSchoolCourseMajor(SchoolCourseMajor schoolCourseMajor)
    {
        return schoolCourseMajorMapper.insertSchoolCourseMajor(schoolCourseMajor);
    }

    /**
     * 修改课程专业关联
     *
     * @param schoolCourseMajor 课程专业关联
     * @return 结果
     */
    @Override
    public int updateSchoolCourseMajor(SchoolCourseMajor schoolCourseMajor)
    {
        return schoolCourseMajorMapper.updateSchoolCourseMajor(schoolCourseMajor);
    }

    /**
     * 批量删除课程专业关联
     *
     * @param courseIds 需要删除的课程专业关联主键
     * @return 结果
     */
    @Override
    public int deleteSchoolCourseMajorByCourseIds(Long[] courseIds)
    {
        return schoolCourseMajorMapper.deleteSchoolCourseMajorByCourseIds(courseIds);
    }

    /**
     * 删除课程专业关联信息
     *
     * @param courseId 课程专业关联主键
     * @return 结果
     */
    @Override
    public int deleteSchoolCourseMajorByCourseId(Long courseId)
    {
        return schoolCourseMajorMapper.deleteSchoolCourseMajorByCourseId(courseId);
    }
}
