package com.CatTree.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.CatTree.common.annotation.Excel;
import com.CatTree.common.core.domain.BaseEntity;

/**
 * 院系专业关联对象 school_college_major
 *
 * @author CatTree
 * @date 2022-03-06
 */
public class SchoolCollegeMajor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 院系ID */
    private Long collegeId;

    /** 专业ID */
    private Long majorId;

    public void setCollegeId(Long collegeId)
    {
        this.collegeId = collegeId;
    }

    public Long getCollegeId()
    {
        return collegeId;
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
            .append("collegeId", getCollegeId())
            .append("majorId", getMajorId())
            .toString();
    }
}
