package com.shen.ssmschoolshop.entity;

import io.swagger.annotations.Api;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by Shen Peihong on 2020/5/8 1:42
 * Description:
 *
 * @since 1.0.0
 */
@Slf4j
public class ResultVO {
    private Integer code;
    private String desc;
    private Map<String,Object> map;

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

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
