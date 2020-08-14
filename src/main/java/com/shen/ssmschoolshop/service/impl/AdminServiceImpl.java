package com.shen.ssmschoolshop.service.impl;


import com.shen.ssmschoolshop.dao.AdminMapper;
import com.shen.ssmschoolshop.entity.Admin;
import com.shen.ssmschoolshop.service.AdminService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Override
    public Admin selectByName(Admin admin) {
        return adminMapper.selectByName(admin);
    }

    @Override
    public List<Admin> selectAllManager() {
        return adminMapper.selectAllManager();
    }

    @Override
    public void addManager(Admin admin) {
        adminMapper.addManager(admin);
    }

    @Override
    public void userTopup(Integer money, Integer userId) {
        //获得库中金额 加上接收的money  再更新库
        Integer getMoney = this.adminMapper.getMoneyByUserId(userId);
        this.adminMapper.userTopup(money+getMoney,userId);
    }

    public void testyixia(){
        Admin admin = this.adminMapper.testBigDecimal();
        System.out.println(admin);;
    }
}
