package com.shen.ssmschoolshop.service;


import com.shen.ssmschoolshop.entity.User;
import com.shen.ssmschoolshop.entity.UserExample;

import java.util.List;

public interface UserService {
    public User selectByPrimaryKey(int userId);
    /*public User selectByPrimaryKeyAndPassword(int userId,String password);*/
    public List<User> selectByExample(UserExample userExample);

    public void insertSelective(User user);

    public void deleteUserById(Integer userid);

    public void updateByPrimaryKeySelective(User user);

    /**
     * 获得所有用户表的用户
     * @return
     */
    public List<User> getAllUser();

}
