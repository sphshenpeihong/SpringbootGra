package com.shen.ssmschoolshop.entity;

import java.io.Serializable;
import java.util.Date;

public class Favorite extends FavoriteKey implements Serializable {
    private Date collecttime;

    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }
}