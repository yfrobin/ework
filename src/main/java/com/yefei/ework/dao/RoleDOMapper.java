package com.yefei.ework.dao;

import com.yefei.ework.entity.DO.RoleDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleDO record);

    RoleDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleDO record);

    int updateByPrimaryKey(RoleDO record);

    List<RoleDO> getByUserId(String userId);

    RoleDO selectByCode(String roleCode);
}