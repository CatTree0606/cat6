package com.CatTree.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.CatTree.common.annotation.Excel;
import com.CatTree.common.core.domain.BaseEntity;

/**
 * 请假信息对象 school_leave
 *
 * @author CatTree
 * @date 2022-03-07
 */
public class SchoolLeave extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 请假ID */
    private Long leaveId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long userId;

    /** 学生名称 */
    @Excel(name = "学生名称")
    private String userName;

    /** 请假原因 */
    @Excel(name = "请假原因")
    private String leaveText;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 老师ID */
    @Excel(name = "老师ID")
    private Long teacherUserId;

    /** 老师名称 */
    @Excel(name = "老师名称")
    private String teacherUseName;

    /** 审核结果 */
    @Excel(name = "审核结果")
    private String auditResult;

    public void setLeaveId(Long leaveId)
    {
        this.leaveId = leaveId;
    }

    public Long getLeaveId()
    {
        return leaveId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setLeaveText(String leaveText)
    {
        this.leaveText = leaveText;
    }

    public String getLeaveText()
    {
        return leaveText;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setTeacherUserId(Long teacherUserId)
    {
        this.teacherUserId = teacherUserId;
    }

    public Long getTeacherUserId()
    {
        return teacherUserId;
    }
    public void setTeacherUseName(String teacherUseName)
    {
        this.teacherUseName = teacherUseName;
    }

    public String getTeacherUseName()
    {
        return teacherUseName;
    }
    public void setAuditResult(String auditResult)
    {
        this.auditResult = auditResult;
    }

    public String getAuditResult()
    {
        return auditResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("leaveId", getLeaveId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("leaveText", getLeaveText())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("teacherUserId", getTeacherUserId())
            .append("teacherUseName", getTeacherUseName())
            .append("auditResult", getAuditResult())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
