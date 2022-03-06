package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SchoolCollegeMajor;

/**
 * 院系专业关联Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-06
 */
public interface SchoolCollegeMajorMapper 
{
    /**
     * 查询院系专业关联
     * 
     * @param collegeId 院系专业关联主键
     * @return 院系专业关联
     */
    public SchoolCollegeMajor selectSchoolCollegeMajorByCollegeId(Long collegeId);

    /**
     * 查询院系专业关联列表
     * 
     * @param schoolCollegeMajor 院系专业关联
     * @return 院系专业关联集合
     */
    public List<SchoolCollegeMajor> selectSchoolCollegeMajorList(SchoolCollegeMajor schoolCollegeMajor);

    /**
     * 新增院系专业关联
     * 
     * @param schoolCollegeMajor 院系专业关联
     * @return 结果
     */
    public int insertSchoolCollegeMajor(SchoolCollegeMajor schoolCollegeMajor);

    /**
     * 修改院系专业关联
     * 
     * @param schoolCollegeMajor 院系专业关联
     * @return 结果
     */
    public int updateSchoolCollegeMajor(SchoolCollegeMajor schoolCollegeMajor);

    /**
     * 删除院系专业关联
     * 
     * @param collegeId 院系专业关联主键
     * @return 结果
     */
    public int deleteSchoolCollegeMajorByCollegeId(Long collegeId);

    /**
     * 批量删除院系专业关联
     * 
     * @param collegeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolCollegeMajorByCollegeIds(Long[] collegeIds);
}
