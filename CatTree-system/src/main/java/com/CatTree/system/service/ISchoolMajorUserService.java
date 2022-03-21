package com.CatTree.system.service;

import com.CatTree.common.core.domain.entity.SysUser;
import com.CatTree.system.domain.SchoolMajorUser;

import java.util.List;

/**
 * 课程专业关联Service接口
 *
 * @author ruoyi
 * @date 2022-03-21
 */
public interface ISchoolMajorUserService
{

    public SchoolMajorUser selectSchoolMajorUserByMajorId(Long majorId);

    /**
     * 查询课程专业关联列表
     *
     * @param schoolMajorUser 课程专业关联
     * @return 课程专业关联集合
     */
    public List<SchoolMajorUser> selectSchoolMajorUserList(SchoolMajorUser schoolMajorUser);

    /**
     * 新增课程专业关联
     *
     * @param schoolMajorUser 课程专业关联
     * @return 结果
     */
    public int insertSchoolMajorUser(SchoolMajorUser schoolMajorUser);

    /**
     * 修改课程专业关联
     *
     * @param schoolMajorUser 课程专业关联
     * @return 结果
     */
    public int updateSchoolMajorUser(SchoolMajorUser schoolMajorUser);

    /**
     * 批量删除课程专业关联
     *
     * @param userIds 需要删除的课程专业关联主键集合
     * @return 结果
     */
    public int deleteSchoolMajorUserByUserIds(Long[] userIds);

    /**
     * 删除课程专业关联信息
     *
     * @param userId 课程专业关联主键
     * @return 结果
     */
    public int deleteSchoolMajorUserByUserId(Long userId);
}
