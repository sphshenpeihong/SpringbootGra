package com.shen.ssmschoolshop.model.test;

import java.util.List;
import java.util.Map;

/**
 * Created by Shen Peihong on 2020/7/3 10:29
 * Description:
 *
 * @since 1.0.0
 */
public class ComplexVO {
    private String id;
    private Object[] array;
    private Map<String,Object> map;
    private List<User> userList;
    private List<String> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
