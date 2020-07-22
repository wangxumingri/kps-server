package com.wxss.kps.dao;

import com.wxss.kps.rbac.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * delete by primary key
     * @param uid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(String uid);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(User record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(User record);

    /**
     * select by primary key
     * @param uid primary key
     * @return object by primary key
     */
    User selectByPrimaryKey(String uid);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(User record);

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 根据账号查询用户
     * @param username
     * @return
     */
    User findOneByUsername(@Param("username")String username);
}
