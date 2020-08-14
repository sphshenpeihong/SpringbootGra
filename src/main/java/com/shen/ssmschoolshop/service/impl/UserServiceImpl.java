package com.shen.ssmschoolshop.service.impl;


import com.shen.ssmschoolshop.dao.UserMapper;
import com.shen.ssmschoolshop.entity.User;
import com.shen.ssmschoolshop.entity.UserExample;
import com.shen.ssmschoolshop.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<String> testOne(){
        List<String> list = new ArrayList<>();
        list.add("15812481225");
        list.add("1581248122512");
        list.add("15812658569");
        list.add("32");
        List<String> test = userMapper.getTest(list);
        System.out.println("打印一下");
        for (String s : test) {
            System.out.println(s);
        }
        return test;
    }

    @Override
    public List<String> testOne1(){
        List<String> list = new ArrayList<>();
        list.add("15812481225");
        list.add("1581248122512");
        list.add("15812658569");
        list.add("32");
        String s1 = "(\'15812481225\',\'1581248122512\',\'15812658569\',\'32\')";
        List<String> test = userMapper.getTest1(s1);
        System.out.println("打印一下");
        for (String s : test) {
            System.out.println(s);
        }
        return test;

    }

}
