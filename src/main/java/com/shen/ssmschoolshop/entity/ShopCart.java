package com.shen.ssmschoolshop.entity;

import java.io.Serializable;
import java.util.Date;

public class ShopCart extends ShopCartKey implements Serializable {
    private Date catedate;

    private Integer goodsnum;

    public Date getCatedate() {
        return catedate;
    }

    public void setCatedate(Date catedate) {
        this.catedate = catedate;
    }

    public Integer getGoodsnum() {
        return goodsnum;
    }

    public void setGoodsnum(Integer goodsnum) {
        this.goodsnum = goodsnum;
    }
}