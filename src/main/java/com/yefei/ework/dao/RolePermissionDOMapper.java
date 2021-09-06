package com.yefei.ework.dao;

import com.yefei.ework.entity.DO.RolePermissionDO;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(RolePermissionDO record);

    RolePermissionDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePermissionDO record);

    int updateByPrimaryKey(RolePermissionDO record);
}