package com.CatTree.system.service;

import java.util.List;
import com.CatTree.system.domain.SchoolClass;

/**
 * 班级信息Service接口
 *
 * @author CatTree
 * @date 2022-03-07
 */
public interface ISchoolClassService
{
    /**
     * 查询班级信息
     *
     * @param classId 班级信息主键
     * @return 班级信息
     */
    public SchoolClass selectSchoolClassByClassId(Long classId);

    /**
     * 查询班级信息列表
     *
     * @param schoolClass 班级信息
     * @return 班级信息集合
     */
    public List<SchoolClass> selectSchoolClassList(SchoolClass schoolClass);

    /**
     * 新增班级信息
     *
     * @param schoolClass 班级信息
     * @return 结果
     */
    public int insertSchoolClass(SchoolClass schoolClass);

    /**
     * 修改班级信息
     *
     * @param schoolClass 班级信息
     * @return 结果
     */
    public int updateSchoolClass(SchoolClass schoolClass);

    /**
     * 批量删除班级信息
     *
     * @param classIds 需要删除的班级信息主键集合
     * @return 结果
     */
    public int deleteSchoolClassByClassIds(Long[] classIds);

    /**
     * 删除班级信息信息
     *
     * @param classId 班级信息主键
     * @return 结果
     */
    public int deleteSchoolClassByClassId(Long classId);
}
