package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SchoolAttendanceDetailMapper;
import com.ruoyi.system.domain.SchoolAttendanceDetail;
import com.ruoyi.system.service.ISchoolAttendanceDetailService;

/**
 * 考勤明细Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-09
 */
@Service
public class SchoolAttendanceDetailServiceImpl implements ISchoolAttendanceDetailService
{
    @Autowired
    private SchoolAttendanceDetailMapper schoolAttendanceDetailMapper;

    /**
     * 查询考勤明细
     *
     * @param attendanceId 考勤明细主键
     * @return 考勤明细
     */
    @Override
    public List<SchoolAttendanceDetail> selectSchoolAttendanceDetailByAttendanceId(Long attendanceId)
    {
        return schoolAttendanceDetailMapper.selectSchoolAttendanceDetailByAttendanceId(attendanceId);
    }

    /**
     * 查询考勤明细列表
     *
     * @param schoolAttendanceDetail 考勤明细
     * @return 考勤明细
     */
    @Override
    public List<SchoolAttendanceDetail> selectSchoolAttendanceDetailList(SchoolAttendanceDetail schoolAttendanceDetail)
    {
        return schoolAttendanceDetailMapper.selectSchoolAttendanceDetailList(schoolAttendanceDetail);
    }

    /**
     * 新增考勤明细
     *
     * @param schoolAttendanceDetail 考勤明细
     * @return 结果
     */
    @Override
    public int insertSchoolAttendanceDetail(SchoolAttendanceDetail schoolAttendanceDetail)
    {
        schoolAttendanceDetail.setCreateTime(DateUtils.getNowDate());
        return schoolAttendanceDetailMapper.insertSchoolAttendanceDetail(schoolAttendanceDetail);
    }

    /**
     * 修改考勤明细
     *
     * @param schoolAttendanceDetail 考勤明细
     * @return 结果
     */
    @Override
    public int updateSchoolAttendanceDetail(SchoolAttendanceDetail schoolAttendanceDetail)
    {
        return schoolAttendanceDetailMapper.updateSchoolAttendanceDetail(schoolAttendanceDetail);
    }

    /**
     * 批量删除考勤明细
     *
     * @param attendanceIds 需要删除的考勤明细主键
     * @return 结果
     */
    @Override
    public int deleteSchoolAttendanceDetailByAttendanceIds(Long[] attendanceIds)
    {
        return schoolAttendanceDetailMapper.deleteSchoolAttendanceDetailByAttendanceIds(attendanceIds);
    }

    /**
     * 删除考勤明细信息
     *
     * @param attendanceId 考勤明细主键
     * @return 结果
     */
    @Override
    public int deleteSchoolAttendanceDetailByAttendanceId(Long attendanceId)
    {
        return schoolAttendanceDetailMapper.deleteSchoolAttendanceDetailByAttendanceId(attendanceId);
    }
}
