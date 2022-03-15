package com.CatTree.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CatTree.system.mapper.SchoolClassUserMapper;
import com.CatTree.system.domain.SchoolClassUser;
import com.CatTree.system.service.ISchoolClassUserService;
import org.springframework.util.CollectionUtils;

/**
 * 班级学生关联Service业务层处理
 *
 * @author CatTree
 * @date 2022-03-07
 */
@Service
public class SchoolClassUserServiceImpl implements ISchoolClassUserService
{
    @Autowired
    private SchoolClassUserMapper schoolClassUserMapper;

    /**
     * 查询班级学生关联
     *
     * @param classId 班级学生关联主键
     * @return 班级学生关联
     */
    @Override
    public SchoolClassUser selectSchoolClassUserByClassId(Long classId)
    {
        SchoolClassUser u = new SchoolClassUser();
        List<SchoolClassUser> schoolClassUsers = schoolClassUserMapper.selectSchoolClassUserByClassId(classId);
        if(!CollectionUtils.isEmpty(schoolClassUsers)){
            List<Long> collect = schoolClassUsers.stream().map(s -> s.getUserId()).collect(Collectors.toList());
            u.setUserIds(collect.toArray(new Long[collect.size()]));
        }
        u.setClassId(classId);

        return u;
    }

    /**
     * 查询班级学生关联列表
     *
     * @param schoolClassUser 班级学生关联
     * @return 班级学生关联
     */
    @Override
    public List<SchoolClassUser> selectSchoolClassUserList(SchoolClassUser schoolClassUser)
    {
        return schoolClassUserMapper.selectSchoolClassUserList(schoolClassUser);
    }

    /**
     * 新增班级学生关联
     *
     * @param schoolClassUser 班级学生关联
     * @return 结果
     */
    @Override
    public int insertSchoolClassUser(SchoolClassUser schoolClassUser)
    {
        schoolClassUserMapper.deleteSchoolClassUserByClassId(schoolClassUser.getClassId());

        int ii = 0;
        if(null != schoolClassUser.getUserIds() && schoolClassUser.getUserIds().length>0){
            for (int i = 0; i < schoolClassUser.getUserIds().length; i++) {
                SchoolClassUser user = new SchoolClassUser();
                user.setClassId(schoolClassUser.getClassId());
                user.setUserId(schoolClassUser.getUserIds()[i]);
                ii= schoolClassUserMapper.insertSchoolClassUser(user);
            }
        }else{
            ii = schoolClassUserMapper.insertSchoolClassUser(schoolClassUser);
        }
        return ii;
    }

    /**
     * 修改班级学生关联
     *
     * @param schoolClassUser 班级学生关联
     * @return 结果
     */
    @Override
    public int updateSchoolClassUser(SchoolClassUser schoolClassUser)
    {
        return schoolClassUserMapper.updateSchoolClassUser(schoolClassUser);
    }

    /**
     * 批量删除班级学生关联
     *
     * @param classIds 需要删除的班级学生关联主键
     * @return 结果
     */
    @Override
    public int deleteSchoolClassUserByClassIds(Long[] classIds)
    {
        return schoolClassUserMapper.deleteSchoolClassUserByClassIds(classIds);
    }

    /**
     * 删除班级学生关联信息
     *
     * @param classId 班级学生关联主键
     * @return 结果
     */
    @Override
    public int deleteSchoolClassUserByClassId(Long classId)
    {
        return schoolClassUserMapper.deleteSchoolClassUserByClassId(classId);
    }
}
