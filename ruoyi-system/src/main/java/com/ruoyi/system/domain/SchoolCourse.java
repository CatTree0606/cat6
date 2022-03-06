package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 课程信息对象 school_course
 *
 * @author ruoyi
 * @date 2022-03-06
 */
public class SchoolCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程ID */
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 老师ID */
    @Excel(name = "老师ID")
    private Long teacherUserId;

    private String teacherName;

    //专业
    private Long[] majorId;

    private List<SchoolMajor> majorList;
    private List<SysUser> teacherList;


    public List<SysUser> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<SysUser> teacherList) {
        this.teacherList = teacherList;
    }

    public List<SchoolMajor> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<SchoolMajor> majorList) {
        this.majorList = majorList;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long[] getMajorId() {
        return majorId;
    }

    public void setMajorId(Long[] majorId) {
        this.majorId = majorId;
    }

    public void setCourseId(Long courseId)
    {
        this.courseId = courseId;
    }

    public Long getCourseId()
    {
        return courseId;
    }
    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseName()
    {
        return courseName;
    }
    public void setTeacherUserId(Long teacherUserId)
    {
        this.teacherUserId = teacherUserId;
    }

    public Long getTeacherUserId()
    {
        return teacherUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("courseId", getCourseId())
            .append("courseName", getCourseName())
            .append("teacherUserId", getTeacherUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
