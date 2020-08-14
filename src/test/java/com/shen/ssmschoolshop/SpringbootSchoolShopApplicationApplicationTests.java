package com.shen.ssmschoolshop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

@RunWith(SpringRunner.class)
@SpringBootTest
@Controller
public class SpringbootSchoolShopApplicationApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void rukou(){
        System.out.println("进来了吗？");
    }

}
