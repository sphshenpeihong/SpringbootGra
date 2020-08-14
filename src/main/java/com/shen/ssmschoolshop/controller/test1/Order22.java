package com.shen.ssmschoolshop.controller.test1;

import org.junit.Test;

/**
 * Created by Shen Peihong on 2020/7/3 11:30
 * Description:
 *
 * @since 1.0.0
 */
public class Order22 {

    static {System.out.println("外部类static");}
    {System.out.println("外部类构造快");}
    public Order22(){System.out.println("外部类的构造方法");}

    @Test
    public void test(){
        new Order22(){{System.out.println("我现在是Order22的匿名内部类的构造块了");}};
    }

    class inner{
        {System.out.println("内部类的构造快");}
        public inner(){System.out.println("内部类的构造方法");}
    }
}
