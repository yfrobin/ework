package com.yefei.ework.dao;

import com.yefei.ework.entity.DO.PermissionDO;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(PermissionDO record);


    PermissionDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PermissionDO record);

    int updateByPrimaryKey(PermissionDO record);

    PermissionDO selectByCode(String permissionCode);
}