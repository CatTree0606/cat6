package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysUserFace;

/**
 * 用户人脸识别信息
Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-11
 */
public interface SysUserFaceMapper 
{
    /**
     * 查询用户人脸识别信息

     * 
     * @param userFaceId 用户人脸识别信息
主键
     * @return 用户人脸识别信息

     */
    public SysUserFace selectSysUserFaceByUserFaceId(Long userFaceId);

    /**
     * 查询用户人脸识别信息
列表
     * 
     * @param sysUserFace 用户人脸识别信息

     * @return 用户人脸识别信息
集合
     */
    public List<SysUserFace> selectSysUserFaceList(SysUserFace sysUserFace);

    /**
     * 新增用户人脸识别信息

     * 
     * @param sysUserFace 用户人脸识别信息

     * @return 结果
     */
    public int insertSysUserFace(SysUserFace sysUserFace);

    /**
     * 修改用户人脸识别信息

     * 
     * @param sysUserFace 用户人脸识别信息

     * @return 结果
     */
    public int updateSysUserFace(SysUserFace sysUserFace);

    /**
     * 删除用户人脸识别信息

     * 
     * @param userFaceId 用户人脸识别信息
主键
     * @return 结果
     */
    public int deleteSysUserFaceByUserFaceId(Long userFaceId);

    /**
     * 批量删除用户人脸识别信息

     * 
     * @param userFaceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserFaceByUserFaceIds(Long[] userFaceIds);
}
