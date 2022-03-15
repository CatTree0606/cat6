package com.CatTree.system.service;

import java.util.List;
import com.CatTree.system.domain.SysUserFace;

/**
 * 用户人脸识别信息
Service接口
 *
 * @author CatTree
 * @date 2022-03-11
 */
public interface ISysUserFaceService
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
     * 批量删除用户人脸识别信息

     *
     * @param userFaceIds 需要删除的用户人脸识别信息
主键集合
     * @return 结果
     */
    public int deleteSysUserFaceByUserFaceIds(Long[] userFaceIds);

    /**
     * 删除用户人脸识别信息
信息
     *
     * @param userFaceId 用户人脸识别信息
主键
     * @return 结果
     */
    public int deleteSysUserFaceByUserFaceId(Long userFaceId);
}
