package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户人脸识别信息
对象 sys_user_face
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
public class SysUserFace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long userFaceId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 人脸token */
    @Excel(name = "人脸token")
    private String faceToken;

    public void setUserFaceId(Long userFaceId) 
    {
        this.userFaceId = userFaceId;
    }

    public Long getUserFaceId() 
    {
        return userFaceId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setFaceToken(String faceToken) 
    {
        this.faceToken = faceToken;
    }

    public String getFaceToken() 
    {
        return faceToken;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userFaceId", getUserFaceId())
            .append("userId", getUserId())
            .append("faceToken", getFaceToken())
            .toString();
    }
}
