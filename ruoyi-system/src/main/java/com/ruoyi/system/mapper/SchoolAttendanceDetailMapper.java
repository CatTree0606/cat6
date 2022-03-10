package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SchoolAttendanceDetail;

/**
 * 考勤明细Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-09
 */
public interface SchoolAttendanceDetailMapper
{
    /**
     * 查询考勤明细
     *
     * @param attendanceId 考勤明细主键
     * @return 考勤明细
     */
    public List<SchoolAttendanceDetail> selectSchoolAttendanceDetailByAttendanceId(Long attendanceId);

    /**
     * 查询考勤明细列表
     *
     * @param schoolAttendanceDetail 考勤明细
     * @return 考勤明细集合
     */
    public List<SchoolAttendanceDetail> selectSchoolAttendanceDetailList(SchoolAttendanceDetail schoolAttendanceDetail);

    /**
     * 新增考勤明细
     *
     * @param schoolAttendanceDetail 考勤明细
     * @return 结果
     */
    public int insertSchoolAttendanceDetail(SchoolAttendanceDetail schoolAttendanceDetail);

    /**
     * 修改考勤明细
     *
     * @param schoolAttendanceDetail 考勤明细
     * @return 结果
     */
    public int updateSchoolAttendanceDetail(SchoolAttendanceDetail schoolAttendanceDetail);

    /**
     * 删除考勤明细
     *
     * @param attendanceId 考勤明细主键
     * @return 结果
     */
    public int deleteSchoolAttendanceDetailByAttendanceId(Long attendanceId);

    /**
     * 批量删除考勤明细
     *
     * @param attendanceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolAttendanceDetailByAttendanceIds(Long[] attendanceIds);
}
