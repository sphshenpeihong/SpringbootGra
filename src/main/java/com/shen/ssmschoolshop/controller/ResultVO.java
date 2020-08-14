package com.shen.ssmschoolshop.controller;

/**
 * Created by Shen Peihong on 2020/6/29 15:50
 * Description:
 *
 * @since 1.0.0
 */
public class ResultVO {

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultVO(){}

    public ResultVO(String code, String desc, Object data1) {
        this.code = code;
        this.desc = desc;
        this.data1 = data1;
    }

    public Object getData1() {
        return data1;
    }

    public void setData1(Object data1) {
        this.data1 = data1;
    }

    private Object data1;

    public Object getData() {
        return data1;
    }

    public ResultVO(Object data) {
        this.data1 = data;
    }

    public void setData(Object data) {
        this.data1 = data;
    }
}
