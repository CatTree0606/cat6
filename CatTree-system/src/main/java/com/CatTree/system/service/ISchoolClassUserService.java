package com.CatTree.system.service;

import java.util.List;
import com.CatTree.system.domain.SchoolClassUser;

/**
 * 班级学生关联Service接口
 *
 * @author CatTree
 * @date 2022-03-07
 */
public interface ISchoolClassUserService
{
    /**
     * 查询班级学生关联
     *
     * @param classId 班级学生关联主键
     * @return 班级学生关联
     */
    public SchoolClassUser selectSchoolClassUserByClassId(Long classId);

    /**
     * 查询班级学生关联列表
     *
     * @param schoolClassUser 班级学生关联
     * @return 班级学生关联集合
     */
    public List<SchoolClassUser> selectSchoolClassUserList(SchoolClassUser schoolClassUser);

    /**
     * 新增班级学生关联
     *
     * @param schoolClassUser 班级学生关联
     * @return 结果
     */
    public int insertSchoolClassUser(SchoolClassUser schoolClassUser);

    /**
     * 修改班级学生关联
     *
     * @param schoolClassUser 班级学生关联
     * @return 结果
     */
    public int updateSchoolClassUser(SchoolClassUser schoolClassUser);

    /**
     * 批量删除班级学生关联
     *
     * @param classIds 需要删除的班级学生关联主键集合
     * @return 结果
     */
    public int deleteSchoolClassUserByClassIds(Long[] classIds);

    /**
     * 删除班级学生关联信息
     *
     * @param classId 班级学生关联主键
     * @return 结果
     */
    public int deleteSchoolClassUserByClassId(Long classId);
}
