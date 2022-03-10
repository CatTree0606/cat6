package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考勤明细对象 school_attendance_detail
 * 
 * @author ruoyi
 * @date 2022-03-09
 */
public class SchoolAttendanceDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考勤ID */
    private Long attendanceId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long userId;

    /** 考勤状态 */
    @Excel(name = "考勤状态")
    private String status;

    /** 是否迟到 */
    @Excel(name = "是否迟到")
    private String isLate;

    /** 考勤时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

    /** 考勤类型 */
    @Excel(name = "考勤类型")
    private String signType;

    /** 签退 */
    @Excel(name = "签退")
    private String signOut;

    /** 签退时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签退时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signOutTime;

    public void setAttendanceId(Long attendanceId) 
    {
        this.attendanceId = attendanceId;
    }

    public Long getAttendanceId() 
    {
        return attendanceId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setIsLate(String isLate) 
    {
        this.isLate = isLate;
    }

    public String getIsLate() 
    {
        return isLate;
    }
    public void setSignTime(Date signTime) 
    {
        this.signTime = signTime;
    }

    public Date getSignTime() 
    {
        return signTime;
    }
    public void setSignType(String signType) 
    {
        this.signType = signType;
    }

    public String getSignType() 
    {
        return signType;
    }
    public void setSignOut(String signOut) 
    {
        this.signOut = signOut;
    }

    public String getSignOut() 
    {
        return signOut;
    }
    public void setSignOutTime(Date signOutTime) 
    {
        this.signOutTime = signOutTime;
    }

    public Date getSignOutTime() 
    {
        return signOutTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attendanceId", getAttendanceId())
            .append("userId", getUserId())
            .append("status", getStatus())
            .append("isLate", getIsLate())
            .append("signTime", getSignTime())
            .append("signType", getSignType())
            .append("signOut", getSignOut())
            .append("signOutTime", getSignOutTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
