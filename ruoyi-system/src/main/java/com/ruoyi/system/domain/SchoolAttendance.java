package com.ruoyi.system.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考勤对象 school_attendance
 *
 * @author ruoyi
 * @date 2022-03-09
 */
public class SchoolAttendance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考勤ID */
    private Long attendanceId;

    /** 老师ID */
    @Excel(name = "老师ID")
    private Long teacherUserId;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "课程开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date courseStart;

    /** 课程结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "课程结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date courseEnd;

    /** 总人数 */
    @Excel(name = "总人数")
    private Long totalNumber;

    /** 已签到人数 */
    @Excel(name = "已签到人数")
    private Long signIn;

    /** 未签到人数 */
    @Excel(name = "未签到人数")
    private Long noSignIn;

    private List<SchoolCourse> courseList;

    public List<SchoolCourse> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<SchoolCourse> courseList) {
        this.courseList = courseList;
    }

    public void setAttendanceId(Long attendanceId)
    {
        this.attendanceId = attendanceId;
    }

    public Long getAttendanceId()
    {
        return attendanceId;
    }
    public void setTeacherUserId(Long teacherUserId)
    {
        this.teacherUserId = teacherUserId;
    }

    public Long getTeacherUserId()
    {
        return teacherUserId;
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
    public void setCourseStart(Date courseStart)
    {
        this.courseStart = courseStart;
    }

    public Date getCourseStart()
    {
        return courseStart;
    }
    public void setCourseEnd(Date courseEnd)
    {
        this.courseEnd = courseEnd;
    }

    public Date getCourseEnd()
    {
        return courseEnd;
    }
    public void setTotalNumber(Long totalNumber)
    {
        this.totalNumber = totalNumber;
    }

    public Long getTotalNumber()
    {
        return totalNumber;
    }
    public void setSignIn(Long signIn)
    {
        this.signIn = signIn;
    }

    public Long getSignIn()
    {
        return signIn;
    }
    public void setNoSignIn(Long noSignIn)
    {
        this.noSignIn = noSignIn;
    }

    public Long getNoSignIn()
    {
        return noSignIn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attendanceId", getAttendanceId())
            .append("teacherUserId", getTeacherUserId())
            .append("courseId", getCourseId())
            .append("courseName", getCourseName())
            .append("courseStart", getCourseStart())
            .append("courseEnd", getCourseEnd())
            .append("totalNumber", getTotalNumber())
            .append("signIn", getSignIn())
            .append("noSignIn", getNoSignIn())
            .append("createTime", getCreateTime())
            .toString();
    }
}
