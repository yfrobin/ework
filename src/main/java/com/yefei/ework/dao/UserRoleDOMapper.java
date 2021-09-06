package com.yefei.ework.dao;

import com.yefei.ework.entity.DO.UserRoleDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRoleDO record);

    UserRoleDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRoleDO record);

    int updateByPrimaryKey(UserRoleDO record);
}