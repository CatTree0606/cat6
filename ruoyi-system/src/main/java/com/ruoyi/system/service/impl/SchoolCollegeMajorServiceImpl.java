package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SchoolCollegeMajorMapper;
import com.ruoyi.system.domain.SchoolCollegeMajor;
import com.ruoyi.system.service.ISchoolCollegeMajorService;

/**
 * 院系专业关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-06
 */
@Service
public class SchoolCollegeMajorServiceImpl implements ISchoolCollegeMajorService 
{
    @Autowired
    private SchoolCollegeMajorMapper schoolCollegeMajorMapper;

    /**
     * 查询院系专业关联
     * 
     * @param collegeId 院系专业关联主键
     * @return 院系专业关联
     */
    @Override
    public SchoolCollegeMajor selectSchoolCollegeMajorByCollegeId(Long collegeId)
    {
        return schoolCollegeMajorMapper.selectSchoolCollegeMajorByCollegeId(collegeId);
    }

    /**
     * 查询院系专业关联列表
     * 
     * @param schoolCollegeMajor 院系专业关联
     * @return 院系专业关联
     */
    @Override
    public List<SchoolCollegeMajor> selectSchoolCollegeMajorList(SchoolCollegeMajor schoolCollegeMajor)
    {
        return schoolCollegeMajorMapper.selectSchoolCollegeMajorList(schoolCollegeMajor);
    }

    /**
     * 新增院系专业关联
     * 
     * @param schoolCollegeMajor 院系专业关联
     * @return 结果
     */
    @Override
    public int insertSchoolCollegeMajor(SchoolCollegeMajor schoolCollegeMajor)
    {
        return schoolCollegeMajorMapper.insertSchoolCollegeMajor(schoolCollegeMajor);
    }

    /**
     * 修改院系专业关联
     * 
     * @param schoolCollegeMajor 院系专业关联
     * @return 结果
     */
    @Override
    public int updateSchoolCollegeMajor(SchoolCollegeMajor schoolCollegeMajor)
    {
        return schoolCollegeMajorMapper.updateSchoolCollegeMajor(schoolCollegeMajor);
    }

    /**
     * 批量删除院系专业关联
     * 
     * @param collegeIds 需要删除的院系专业关联主键
     * @return 结果
     */
    @Override
    public int deleteSchoolCollegeMajorByCollegeIds(Long[] collegeIds)
    {
        return schoolCollegeMajorMapper.deleteSchoolCollegeMajorByCollegeIds(collegeIds);
    }

    /**
     * 删除院系专业关联信息
     * 
     * @param collegeId 院系专业关联主键
     * @return 结果
     */
    @Override
    public int deleteSchoolCollegeMajorByCollegeId(Long collegeId)
    {
        return schoolCollegeMajorMapper.deleteSchoolCollegeMajorByCollegeId(collegeId);
    }
}
