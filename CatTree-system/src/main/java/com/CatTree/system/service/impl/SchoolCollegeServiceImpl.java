package com.CatTree.system.service.impl;

import java.util.List;
import com.CatTree.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CatTree.system.mapper.SchoolCollegeMapper;
import com.CatTree.system.domain.SchoolCollege;
import com.CatTree.system.service.ISchoolCollegeService;

/**
 * 院系信息Service业务层处理
 *
 * @author CatTree
 * @date 2022-03-06
 */
@Service
public class SchoolCollegeServiceImpl implements ISchoolCollegeService
{
    @Autowired
    private SchoolCollegeMapper schoolCollegeMapper;

    /**
     * 查询院系信息
     *
     * @param collegeId 院系信息主键
     * @return 院系信息
     */
    @Override
    public SchoolCollege selectSchoolCollegeByCollegeId(Long collegeId)
    {
        return schoolCollegeMapper.selectSchoolCollegeByCollegeId(collegeId);
    }

    /**
     * 查询院系信息列表
     *
     * @param schoolCollege 院系信息
     * @return 院系信息
     */
    @Override
    public List<SchoolCollege> selectSchoolCollegeList(SchoolCollege schoolCollege)
    {
        return schoolCollegeMapper.selectSchoolCollegeList(schoolCollege);
    }

    /**
     * 新增院系信息
     *
     * @param schoolCollege 院系信息
     * @return 结果
     */
    @Override
    public int insertSchoolCollege(SchoolCollege schoolCollege)
    {
        schoolCollege.setCreateTime(DateUtils.getNowDate());
        return schoolCollegeMapper.insertSchoolCollege(schoolCollege);
    }

    /**
     * 修改院系信息
     *
     * @param schoolCollege 院系信息
     * @return 结果
     */
    @Override
    public int updateSchoolCollege(SchoolCollege schoolCollege)
    {
        schoolCollege.setUpdateTime(DateUtils.getNowDate());
        return schoolCollegeMapper.updateSchoolCollege(schoolCollege);
    }

    /**
     * 批量删除院系信息
     *
     * @param collegeIds 需要删除的院系信息主键
     * @return 结果
     */
    @Override
    public int deleteSchoolCollegeByCollegeIds(Long[] collegeIds)
    {
        return schoolCollegeMapper.deleteSchoolCollegeByCollegeIds(collegeIds);
    }

    /**
     * 删除院系信息信息
     *
     * @param collegeId 院系信息主键
     * @return 结果
     */
    @Override
    public int deleteSchoolCollegeByCollegeId(Long collegeId)
    {
        return schoolCollegeMapper.deleteSchoolCollegeByCollegeId(collegeId);
    }
}
