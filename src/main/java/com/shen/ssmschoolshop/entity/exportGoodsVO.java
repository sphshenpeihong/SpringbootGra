package com.shen.ssmschoolshop.entity;

import io.swagger.models.auth.In;

import java.io.Serializable;

/**
 * Created by Shen Peihong on 2020/5/10 14:09
 * Description:
 *
 * @since 1.0.0
 */
public class exportGoodsVO implements Serializable {

    private String goodsid;
    private String goodsname;
    private String price;
    private String num;
    private String detailcate;
    private String activityid;

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDetailcate() {
        return detailcate;
    }

    public void setDetailcate(String detailcate) {
        this.detailcate = detailcate;
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }
}
