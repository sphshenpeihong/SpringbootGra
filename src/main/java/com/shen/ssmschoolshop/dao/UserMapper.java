package com.shen.ssmschoolshop.dao;


import com.shen.ssmschoolshop.entity.User;
import com.shen.ssmschoolshop.entity.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 获得用户表所有用户
     * @return
     */
    List<User> getAllUser();

    List<String> getTest(List<String> list);

    List<String> getTest1(String test);

}