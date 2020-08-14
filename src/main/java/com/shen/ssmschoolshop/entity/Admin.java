package com.shen.ssmschoolshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Admin implements Serializable {
    private Integer adminid;

    private String adminname;

    private String password;

    //超管1 普管0
    private Integer authority;

    //测试参数
    private BigDecimal test;
    //下面测试mysql的 int类型、datetime类型、varchar类型、float类型

    private Integer intTest;

    private Date dateTest;

    private String stringTest;

    private Float floatTest;

    public Integer getIntTest() {
        return intTest;
    }

    public void setIntTest(Integer intTest) {
        this.intTest = intTest;
    }

    public Date getDateTest() {
        return dateTest;
    }

    public void setDateTest(Date dateTest) {
        this.dateTest = dateTest;
    }

    public String getStringTest() {
        return stringTest;
    }

    public void setStringTest(String stringTest) {
        this.stringTest = stringTest;
    }

    public Float getFloatTest() {
        return floatTest;
    }

    public void setFloatTest(Float floatTest) {
        this.floatTest = floatTest;
    }

    public BigDecimal getTest() {
        return test;
    }

    public void setTest(BigDecimal test) {
        this.test = test;
    }

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