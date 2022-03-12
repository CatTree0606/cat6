package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserFaceMapper;
import com.ruoyi.system.domain.SysUserFace;
import com.ruoyi.system.service.ISysUserFaceService;

/**
 * 用户人脸识别信息
Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
@Service
public class SysUserFaceServiceImpl implements ISysUserFaceService 
{
    @Autowired
    private SysUserFaceMapper sysUserFaceMapper;

    /**
     * 查询用户人脸识别信息

     * 
     * @param userFaceId 用户人脸识别信息
主键
     * @return 用户人脸识别信息

     */
    @Override
    public SysUserFace selectSysUserFaceByUserFaceId(Long userFaceId)
    {
        return sysUserFaceMapper.selectSysUserFaceByUserFaceId(userFaceId);
    }

    /**
     * 查询用户人脸识别信息
列表
     * 
     * @param sysUserFace 用户人脸识别信息

     * @return 用户人脸识别信息

     */
    @Override
    public List<SysUserFace> selectSysUserFaceList(SysUserFace sysUserFace)
    {
        return sysUserFaceMapper.selectSysUserFaceList(sysUserFace);
    }

    /**
     * 新增用户人脸识别信息

     * 
     * @param sysUserFace 用户人脸识别信息

     * @return 结果
     */
    @Override
    public int insertSysUserFace(SysUserFace sysUserFace)
    {
        return sysUserFaceMapper.insertSysUserFace(sysUserFace);
    }

    /**
     * 修改用户人脸识别信息

     * 
     * @param sysUserFace 用户人脸识别信息

     * @return 结果
     */
    @Override
    public int updateSysUserFace(SysUserFace sysUserFace)
    {
        return sysUserFaceMapper.updateSysUserFace(sysUserFace);
    }

    /**
     * 批量删除用户人脸识别信息

     * 
     * @param userFaceIds 需要删除的用户人脸识别信息
主键
     * @return 结果
     */
    @Override
    public int deleteSysUserFaceByUserFaceIds(Long[] userFaceIds)
    {
        return sysUserFaceMapper.deleteSysUserFaceByUserFaceIds(userFaceIds);
    }

    /**
     * 删除用户人脸识别信息
信息
     * 
     * @param userFaceId 用户人脸识别信息
主键
     * @return 结果
     */
    @Override
    public int deleteSysUserFaceByUserFaceId(Long userFaceId)
    {
        return sysUserFaceMapper.deleteSysUserFaceByUserFaceId(userFaceId);
    }
}
