package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SchoolLeave;

/**
 * 请假信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-07
 */
public interface SchoolLeaveMapper 
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
     * 删除请假信息
     * 
     * @param leaveId 请假信息主键
     * @return 结果
     */
    public int deleteSchoolLeaveByLeaveId(Long leaveId);

    /**
     * 批量删除请假信息
     * 
     * @param leaveIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolLeaveByLeaveIds(Long[] leaveIds);
}
