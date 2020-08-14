package com.shen.ssmschoolshop.model;

/**
 * Created by Shen Peihong on 2020/7/2 23:08
 * Description:
 *
 * @since 1.0.0
 */
public class TestResult111 {
    private Integer code;
    private String desc;
    private Object data1;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data1;
    }

    public void setData(Object data) {
        this.data1 = data;
    }

    public TestResult111(Integer code, String desc, Object data1) {
        this.code = code;
        this.desc = desc;
        this.data1 = data1;
    }

    public TestResult111(Object data1){
        this.code = 0;
        this.desc = "成功";
        this.data1 = data1;
    }

    public TestResult111() {
    }
}
