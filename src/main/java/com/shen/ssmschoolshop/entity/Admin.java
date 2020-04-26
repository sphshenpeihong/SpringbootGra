package com.shen.ssmschoolshop.entity;

import java.io.Serializable;

public class Admin implements Serializable {
    private Integer adminid;

    private String adminname;

    private String password;

    //超管1 普管0
    private Integer authority;

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public Admin(Integer adminid, String adminname, String password) {
        this.adminid = adminid;
        this.adminname = adminname;
        this.password = password;
        this.authority = 0;
    }

    public Admin() {
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname == null ? null : adminname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}