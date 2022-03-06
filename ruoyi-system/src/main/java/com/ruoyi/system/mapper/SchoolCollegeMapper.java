package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SchoolCollege;

/**
 * 院系信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-06
 */
public interface SchoolCollegeMapper 
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
     * 删除院系信息
     * 
     * @param collegeId 院系信息主键
     * @return 结果
     */
    public int deleteSchoolCollegeByCollegeId(Long collegeId);

    /**
     * 批量删除院系信息
     * 
     * @param collegeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolCollegeByCollegeIds(Long[] collegeIds);
}
