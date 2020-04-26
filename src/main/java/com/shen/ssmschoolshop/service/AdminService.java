package com.shen.ssmschoolshop.service;


import com.shen.ssmschoolshop.entity.Admin;

import java.util.List;

public interface AdminService {
    public Admin selectByName(Admin admin);

    /**
     * 查询admin库所有管理员
     * @return
     */
    public List<Admin> selectAllManager();

    /**
     * 添加单个后台管理员
     * @param admin
     */
    public void addManager(Admin admin);
}
