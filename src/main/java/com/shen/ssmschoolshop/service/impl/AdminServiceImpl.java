package com.shen.ssmschoolshop.service.impl;


import com.shen.ssmschoolshop.dao.AdminMapper;
import com.shen.ssmschoolshop.entity.Admin;
import com.shen.ssmschoolshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Override
    public Admin selectByName(Admin admin) {
        return adminMapper.selectByName(admin);
    }
}
