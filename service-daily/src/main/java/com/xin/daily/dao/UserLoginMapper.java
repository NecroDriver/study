package com.xin.daily.dao;

import com.xin.daily.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author creator mafh 2019/11/15 15:04
 * @author updater
 * @version 1.0.0
 */
@Mapper
public interface UserLoginMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(UserLogin record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(UserLogin record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    UserLogin selectByPrimaryKey(Integer id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(UserLogin record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(UserLogin record);
}