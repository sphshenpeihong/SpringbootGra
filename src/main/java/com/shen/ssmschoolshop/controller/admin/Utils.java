package com.shen.ssmschoolshop.controller.admin;

import com.shen.ssmschoolshop.service.GoodsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Shen Peihong on 2020/6/18 13:47
 * Description:
 *
 * @since 1.0.0
 */
public class Utils {

    public void xuan(GoodsService goodsService){
        System.out.println(goodsService);
    }


}
