package com.CatTree.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.CatTree.common.annotation.Excel;
import com.CatTree.common.core.domain.BaseEntity;

/**
 * 课程专业关联对象 school_course_major
 *
 * @author CatTree
 * @date 2022-03-06
 */
public class SchoolCourseMajor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程ID */
    private Long courseId;

    /** 专业ID */
    private Long majorId;

    public void setCourseId(Long courseId)
    {
        this.courseId = courseId;
    }

    public Long getCourseId()
    {
        return courseId;
    }
    public void setMajorId(Long majorId)
    {
        this.majorId = majorId;
    }

    public Long getMajorId()
    {
        return majorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("majorId", getMajorId())
            .toString();
    }
}
