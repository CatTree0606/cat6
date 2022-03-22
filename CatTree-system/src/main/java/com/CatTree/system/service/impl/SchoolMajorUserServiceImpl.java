package com.CatTree.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.CatTree.common.core.domain.entity.SysUser;
import com.CatTree.system.domain.SchoolMajorUser;
import com.CatTree.system.mapper.SchoolMajorUserMapper;
import com.CatTree.system.mapper.SysUserMapper;
import com.CatTree.system.service.ISchoolMajorUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


/**
 * @author ruoyi
 * @date 2022-03-21
 */
@Service
public class SchoolMajorUserServiceImpl implements ISchoolMajorUserService {
    @Autowired
    private SchoolMajorUserMapper schoolMajorUserMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询课程专业关联
     *
     * @param userId 课程专业关联主键
     * @return 课程专业关联
     */
    @Override
    public SchoolMajorUser selectSchoolMajorUserByMajorId(Long majorId) {
        List<SchoolMajorUser> schoolMajorUsers = schoolMajorUserMapper.selectSchoolMajorUserByMajorId(majorId);
        List<SysUser> listUser = new ArrayList<>();
        if (!CollectionUtils.isEmpty(schoolMajorUsers)) {
            schoolMajorUsers.stream().forEach(s -> {
                SysUser sysUser = sysUserMapper.selectUserById(s.getUserId());
                listUser.add(sysUser);
            });
        }
        SchoolMajorUser schoolMajorUser = new SchoolMajorUser();
        List<Long> collect = listUser.stream().map(s -> s.getUserId()).collect(Collectors.toList());
        schoolMajorUser.setUserIds(collect.toArray(new Long[collect.size()]));
        return schoolMajorUser;
    }

    /**
     * 查询课程专业关联列表
     *
     * @param schoolMajorUser 课程专业关联
     * @return 课程专业关联
     */
    @Override
    public List<SchoolMajorUser> selectSchoolMajorUserList(SchoolMajorUser schoolMajorUser) {
        return schoolMajorUserMapper.selectSchoolMajorUserList(schoolMajorUser);
    }

    /**
     * 新增课程专业关联
     *
     * @param schoolMajorUser 课程专业关联
     * @return 结果
     */
    @Override
    public int insertSchoolMajorUser(SchoolMajorUser schoolMajorUser) {
        Long[] userIds = schoolMajorUser.getUserIds();
        if (null != userIds && userIds.length > 0) {
            int i1 = schoolMajorUserMapper.deleteSchoolMajorUserByMajorId(schoolMajorUser.getMajorId());
            for (int i = 0; i < userIds.length; i++) {
                SchoolMajorUser user = new SchoolMajorUser();
                user.setUserId(userIds[i]);
                user.setMajorId(schoolMajorUser.getMajorId());
                schoolMajorUserMapper.insertSchoolMajorUser(user);
            }
        }
        return 1;
    }

    /**
     * 修改课程专业关联
     *
     * @param schoolMajorUser 课程专业关联
     * @return 结果
     */
    @Override
    public int updateSchoolMajorUser(SchoolMajorUser schoolMajorUser) {
        return schoolMajorUserMapper.updateSchoolMajorUser(schoolMajorUser);
    }

    /**
     * 批量删除课程专业关联
     *
     * @param userIds 需要删除的课程专业关联主键
     * @return 结果
     */
    @Override
    public int deleteSchoolMajorUserByUserIds(Long[] userIds) {
        return schoolMajorUserMapper.deleteSchoolMajorUserByUserIds(userIds);
    }

    /**
     * 删除课程专业关联信息
     *
     * @param userId 课程专业关联主键
     * @return 结果
     */
    @Override
    public int deleteSchoolMajorUserByUserId(Long userId) {
        return schoolMajorUserMapper.deleteSchoolMajorUserByUserId(userId);
    }
}
