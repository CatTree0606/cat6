package com.CatTree.system.domain;

import com.CatTree.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;


/**
 * 课程专业关联对象 school_major_user
 *
 * @author ruoyi
 * @date 2022-03-21
 */
public class SchoolMajorUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 学生ID
     */
    private Long[] userIds;

    private Long userId;
    /**
     * 专业ID
     */
    private Long majorId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        this.userIds = userIds;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Long getMajorId() {
        return majorId;
    }

    @Override
    public String toString() {
        return "SchoolMajorUser{" +
                "userIds=" + Arrays.toString(userIds) +
                ", userId=" + userId +
                ", majorId=" + majorId +
                '}';
    }
}
