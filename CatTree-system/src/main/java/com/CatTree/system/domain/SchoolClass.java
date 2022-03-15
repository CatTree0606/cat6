package com.CatTree.system.domain;

import com.CatTree.common.core.domain.entity.SysUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.CatTree.common.annotation.Excel;
import com.CatTree.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 班级信息对象 school_class
 *
 * @author CatTree
 * @date 2022-03-07
 */
public class SchoolClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班级ID */
    private Long classId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String className;

    /** 授课老师ID */
    @Excel(name = "授课老师ID")
    private Long teacherUserId;

    /** 授课老师名称 */
    @Excel(name = "授课老师名称")
    private String teacherUserName;


    private List<SysUser> teacherList;

    public List<SysUser> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<SysUser> teacherList) {
        this.teacherList = teacherList;
    }

    public void setClassId(Long classId)
    {
        this.classId = classId;
    }

    public Long getClassId()
    {
        return classId;
    }
    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getClassName()
    {
        return className;
    }
    public void setTeacherUserId(Long teacherUserId)
    {
        this.teacherUserId = teacherUserId;
    }

    public Long getTeacherUserId()
    {
        return teacherUserId;
    }
    public void setTeacherUserName(String teacherUserName)
    {
        this.teacherUserName = teacherUserName;
    }

    public String getTeacherUserName()
    {
        return teacherUserName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classId", getClassId())
            .append("className", getClassName())
            .append("teacherUserId", getTeacherUserId())
            .append("teacherUserName", getTeacherUserName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
