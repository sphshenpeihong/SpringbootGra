package com.shen.ssmschoolshop.model.test;

/**
 * Created by Shen Peihong on 2020/6/8 0:04
 * Description:
 *
 * @since 1.0.0
 */
public class UserVO {
    private Integer id;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserVO(Integer id, User user) {
        this.id = id;
        this.user = user;
    }
}
