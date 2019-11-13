package com.xin.dao;

import com.xin.entity.User;

/**
 * 
 *  
 * @author creator mafh 2019/11/13 18:15
 * @author updater
 *
 * @version 1.0.0
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}