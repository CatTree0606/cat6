package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SchoolAttendance;

/**
 * 考勤Service接口
 * 
 * @author ruoyi
 * @date 2022-03-09
 */
public interface ISchoolAttendanceService 
{
    /**
     * 查询考勤
     * 
     * @param attendanceId 考勤主键
     * @return 考勤
     */
    public SchoolAttendance selectSchoolAttendanceByAttendanceId(Long attendanceId);

    /**
     * 查询考勤列表
     * 
     * @param schoolAttendance 考勤
     * @return 考勤集合
     */
    public List<SchoolAttendance> selectSchoolAttendanceList(SchoolAttendance schoolAttendance);

    /**
     * 新增考勤
     * 
     * @param schoolAttendance 考勤
     * @return 结果
     */
    public int insertSchoolAttendance(SchoolAttendance schoolAttendance);

    /**
     * 修改考勤
     * 
     * @param schoolAttendance 考勤
     * @return 结果
     */
    public int updateSchoolAttendance(SchoolAttendance schoolAttendance);

    /**
     * 批量删除考勤
     * 
     * @param attendanceIds 需要删除的考勤主键集合
     * @return 结果
     */
    public int deleteSchoolAttendanceByAttendanceIds(Long[] attendanceIds);

    /**
     * 删除考勤信息
     * 
     * @param attendanceId 考勤主键
     * @return 结果
     */
    public int deleteSchoolAttendanceByAttendanceId(Long attendanceId);
}
