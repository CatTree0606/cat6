package com.CatTree.system.service;

import java.util.List;
import com.CatTree.system.domain.SchoolCollege;

/**
 * 院系信息Service接口
 *
 * @author CatTree
 * @date 2022-03-06
 */
public interface ISchoolCollegeService
{
    /**
     * 查询院系信息
     *
     * @param collegeId 院系信息主键
     * @return 院系信息
     */
    public SchoolCollege selectSchoolCollegeByCollegeId(Long collegeId);

    /**
     * 查询院系信息列表
     *
     * @param schoolCollege 院系信息
     * @return 院系信息集合
     */
    public List<SchoolCollege> selectSchoolCollegeList(SchoolCollege schoolCollege);

    /**
     * 新增院系信息
     *
     * @param schoolCollege 院系信息
     * @return 结果
     */
    public int insertSchoolCollege(SchoolCollege schoolCollege);

    /**
     * 修改院系信息
     *
     * @param schoolCollege 院系信息
     * @return 结果
     */
    public int updateSchoolCollege(SchoolCollege schoolCollege);

    /**
     * 批量删除院系信息
     *
     * @param collegeIds 需要删除的院系信息主键集合
     * @return 结果
     */
    public int deleteSchoolCollegeByCollegeIds(Long[] collegeIds);

    /**
     * 删除院系信息信息
     *
     * @param collegeId 院系信息主键
     * @return 结果
     */
    public int deleteSchoolCollegeByCollegeId(Long collegeId);
}
