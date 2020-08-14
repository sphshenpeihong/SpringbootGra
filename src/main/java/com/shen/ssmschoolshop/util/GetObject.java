package com.shen.ssmschoolshop.util;

import com.shen.ssmschoolshop.entity.ImagePath;
import com.shen.ssmschoolshop.service.GoodsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Shen Peihong on 2020/6/18 11:22
 * Description:
 *
 * @since 1.0.0
 */
@Component
public class GetObject {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void test(){
        System.out.println(goodsService);
        List<ImagePath> imagePath = goodsService.findImagePath(0);
        System.out.println(imagePath);
    }

}
