package com.shen.ssmschoolshop.model.test;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;


/**
 * Created by Shen Peihong on 2020/6/7 16:41
 * Description:引入validation校验参数框架
 *
 * @since 1.0.0
 */
public class User {

    //@NotNull(message = "用户id不能为空")
    private Integer id;

    private String username;

    private String password;

    private Integer parentId;

    private Float money;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(){}

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, String password, Integer parentId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.parentId = parentId;
    }

}
