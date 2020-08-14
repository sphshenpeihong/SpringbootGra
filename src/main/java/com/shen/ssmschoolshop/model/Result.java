package com.shen.ssmschoolshop.model;

/**
 * Created by Shen Peihong on 2020/7/2 23:08
 * Description:正式使用返回
 *
 * @since 1.0.0
 */
public class Result {
    private Integer code;
    private String desc;
    private Object data;

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
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(Integer code, String desc, Object data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public Result(Object data){
        this.code = 0;
        this.desc = "成功";
        this.data = data;
    }

    public Result() {
    }
}
