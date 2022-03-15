package com.CatTree.system.service;

import java.util.List;
import com.CatTree.system.domain.SchoolLeave;

/**
 * 请假信息Service接口
 *
 * @author CatTree
 * @date 2022-03-07
 */
public interface ISchoolLeaveService
{
    /**
     * 查询请假信息
     *
     * @param leaveId 请假信息主键
     * @return 请假信息
     */
    public SchoolLeave selectSchoolLeaveByLeaveId(Long leaveId);

    /**
     * 查询请假信息列表
     *
     * @param schoolLeave 请假信息
     * @return 请假信息集合
     */
    public List<SchoolLeave> selectSchoolLeaveList(SchoolLeave schoolLeave);

    List<SchoolLeave> selectSchoolLeaveListByTeacher(SchoolLeave schoolLeave);

    /**
     * 新增请假信息
     *
     * @param schoolLeave 请假信息
     * @return 结果
     */
    public int insertSchoolLeave(SchoolLeave schoolLeave);

    /**
     * 修改请假信息
     *
     * @param schoolLeave 请假信息
     * @return 结果
     */
    public int updateSchoolLeave(SchoolLeave schoolLeave);

    /**
     * 批量删除请假信息
     *
     * @param leaveIds 需要删除的请假信息主键集合
     * @return 结果
     */
    public int deleteSchoolLeaveByLeaveIds(Long[] leaveIds);

    /**
     * 删除请假信息信息
     *
     * @param leaveId 请假信息主键
     * @return 结果
     */
    public int deleteSchoolLeaveByLeaveId(Long leaveId);
}
