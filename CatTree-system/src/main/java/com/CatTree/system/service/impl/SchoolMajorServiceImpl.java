package com.CatTree.system.service.impl;

import java.util.List;
import com.CatTree.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CatTree.system.mapper.SchoolMajorMapper;
import com.CatTree.system.domain.SchoolMajor;
import com.CatTree.system.service.ISchoolMajorService;

/**
 * 专业信息Service业务层处理
 *
 * @author CatTree
 * @date 2022-03-06
 */
@Service
public class SchoolMajorServiceImpl implements ISchoolMajorService
{
    @Autowired
    private SchoolMajorMapper schoolMajorMapper;

    /**
     * 查询专业信息
     *
     * @param majorId 专业信息主键
     * @return 专业信息
     */
    @Override
    public SchoolMajor selectSchoolMajorByMajorId(Long majorId)
    {
        return schoolMajorMapper.selectSchoolMajorByMajorId(majorId);
    }

    /**
     * 查询专业信息列表
     *
     * @param schoolMajor 专业信息
     * @return 专业信息
     */
    @Override
    public List<SchoolMajor> selectSchoolMajorList(SchoolMajor schoolMajor)
    {
        return schoolMajorMapper.selectSchoolMajorList(schoolMajor);
    }

    /**
     * 新增专业信息
     *
     * @param schoolMajor 专业信息
     * @return 结果
     */
    @Override
    public int insertSchoolMajor(SchoolMajor schoolMajor)
    {
        schoolMajor.setCreateTime(DateUtils.getNowDate());
        return schoolMajorMapper.insertSchoolMajor(schoolMajor);
    }

    /**
     * 修改专业信息
     *
     * @param schoolMajor 专业信息
     * @return 结果
     */
    @Override
    public int updateSchoolMajor(SchoolMajor schoolMajor)
    {
        schoolMajor.setUpdateTime(DateUtils.getNowDate());
        return schoolMajorMapper.updateSchoolMajor(schoolMajor);
    }

    /**
     * 批量删除专业信息
     *
     * @param majorIds 需要删除的专业信息主键
     * @return 结果
     */
    @Override
    public int deleteSchoolMajorByMajorIds(Long[] majorIds)
    {
        return schoolMajorMapper.deleteSchoolMajorByMajorIds(majorIds);
    }

    /**
     * 删除专业信息信息
     *
     * @param majorId 专业信息主键
     * @return 结果
     */
    @Override
    public int deleteSchoolMajorByMajorId(Long majorId)
    {
        return schoolMajorMapper.deleteSchoolMajorByMajorId(majorId);
    }
}
