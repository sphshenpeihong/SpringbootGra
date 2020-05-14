package com.shen.ssmschoolshop.service.impl;


import com.shen.ssmschoolshop.dao.UserMapper;
import com.shen.ssmschoolshop.entity.User;
import com.shen.ssmschoolshop.entity.UserExample;
import com.shen.ssmschoolshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(int userId) {
        return  userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> selectByExample(UserExample userExample) {
        return userMapper.selectByExample(userExample);
    }

    @Override
    public void insertSelective(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void deleteUserById(Integer userid) {
        userMapper.deleteByPrimaryKey(userid);
    }

    @Override
    public void updateByPrimaryKeySelective(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }


}
