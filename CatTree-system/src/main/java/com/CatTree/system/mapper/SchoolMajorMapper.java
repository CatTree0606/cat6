package com.CatTree.system.mapper;

import java.util.List;
import com.CatTree.system.domain.SchoolMajor;

/**
 * 专业信息Mapper接口
 *
 * @author CatTree
 * @date 2022-03-06
 */
public interface SchoolMajorMapper
{
    /**
     * 查询专业信息
     *
     * @param majorId 专业信息主键
     * @return 专业信息
     */
    public SchoolMajor selectSchoolMajorByMajorId(Long majorId);

    /**
     * 查询专业信息列表
     *
     * @param schoolMajor 专业信息
     * @return 专业信息集合
     */
    public List<SchoolMajor> selectSchoolMajorList(SchoolMajor schoolMajor);

    /**
     * 新增专业信息
     *
     * @param schoolMajor 专业信息
     * @return 结果
     */
    public int insertSchoolMajor(SchoolMajor schoolMajor);

    /**
     * 修改专业信息
     *
     * @param schoolMajor 专业信息
     * @return 结果
     */
    public int updateSchoolMajor(SchoolMajor schoolMajor);

    /**
     * 删除专业信息
     *
     * @param majorId 专业信息主键
     * @return 结果
     */
    public int deleteSchoolMajorByMajorId(Long majorId);

    /**
     * 批量删除专业信息
     *
     * @param majorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolMajorByMajorIds(Long[] majorIds);
}
