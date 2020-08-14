package com.shen.ssmschoolshop.controller.test1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shen.ssmschoolshop.controller.ResultVO;
import com.shen.ssmschoolshop.model.test.User;
import com.shen.ssmschoolshop.model.test.UserVO;
import org.junit.Test;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Shen Peihong on 2020/6/7 16:25
 * Description:
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping("/mgr")
public class TestMgrCtl {

    @RequestMapping("test000.do")
    public ResultVO test000(){
        //List<VO类> 对某个元素进行分组，返回是一个Map类型，左边是分组类型，右边是分组后的值，类型是List<原来的VO类型>   一个参数的话默认就是右边是List类型的
        List<User> list = new ArrayList<>();
        User u1 = new User(1,"zhangsan","zhangsan",-1);
        User u2 = new User(2,"zz","zz",1);
        User u3 = new User(3,"ww","ww",1);
        User u4 = new User(4,"dd","dd",-1);
        User u5 = new User(5,"cc","cc",-1);
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u5);
        Map<Integer, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getParentId));
        //返回分组列表给前端
        ResultVO resultVO = new ResultVO(collect);
        resultVO.setCode("0");
        resultVO.setDesc("成功");
        return resultVO;
    }
    @RequestMapping("test00")
    public ResultVO test00(){
        String json = "[{\"appId\":\"learnonline\",\"appName\":\"骄子大学\"},{\"appId\":\"appId\",\"appName\":\"城投大学\"}]";
        String json1 = "";
        JSONArray objects = JSON.parseArray("[]");
        ResultVO resultVO = new ResultVO(objects);
        resultVO.setData(objects);
        return resultVO;
    }

    @RequestMapping("/test01.do")
    public ResultVO test01(){
        List<Map<String,Object>> list =new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("username","123");
        map.put("password","456");
        map.put("password1","456");
        map.put("password2","456");
        Map<String,Object> map1 = new HashMap<>();
        map1.put("username","123");
        map1.put("password","456");
        map1.put("password1","456");
        map1.put("password2","456");
        list.add(map);
        list.add(map1);
        ResultVO resultVO = new ResultVO(list);
        return resultVO;
    }

    @Test
    public void test123(){
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list.add("123");
        list1.add("456");
        list.addAll(list1);
        System.out.println(list);
        System.out.println(list.addAll(list1));
    }

    @RequestMapping("/test0.do")
    public void test0(String username, HttpServletRequest request){
        System.out.println(request.getParameter("password"));
        System.out.println(username);
    }

    @RequestMapping("/test1.do")
    public void test1(){
        System.out.println("测试");
    }

    /**
     * PO类变量加校验
     */
    @RequestMapping("/test2.do")
    public void test2(User user,@RequestParam(name = "pid1",defaultValue = "400") Integer pid){
        //System.out.println();
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(pid);

    }

    /**
     * PO类变量加校验 使用/的方式 不使用?的传统方式传参
     * RestFul风格传参的话，@PathVariable这个注解绝逼不可以省略
     */
    @RequestMapping("/test3.do/{pid}/{pid1}/{pid2}")
    public void test3(@PathVariable(name = "pid") Integer pid,@PathVariable Integer pid1,@PathVariable Integer pid2){
        System.out.println(pid);
        System.out.println(pid1);
        System.out.println(pid2);

    }

    /**
     * 前端传递一个数组
     */
    @RequestMapping("/test4.do")
    public void test4(Integer[] array){
        for (Integer integer : array) {
            System.out.println("打印："+integer);
        }

    }

    @RequestMapping("/test5.do")
    public void test5(UserVO userVO,User user,int id,Float money){
        System.out.println(userVO.getId());
        System.out.println(user.getId());
        System.out.println(id);
        System.out.println(money);

    }

    @RequestMapping("/test6.do")
    public void test6(UserVO userVO){
        /*System.out.println(userVO.getUser().getUsername());
        System.out.println(userVO.getUser().getPassword());*/

    }

    /**
     * 前端不是传递PO类的成员变量，而是一下子直接传递一个JSON对象中
     * 后端使用String接收，然后使用fastJSON去转换
     */
    @RequestMapping("/test7.do")
    public void test7(User user){
        //Controller层不需要去将形参转换成JSON对象了，因为类上面已经是使用@RestContoller了
        //如果前端传的是JSON字符串的话，那么后端可以直接转成对应的类型
        String s = JSONObject.toJSONString(user);
        JSONObject jsonObject = JSONObject.parseObject(s);
        User user1 = JSONObject.parseObject(s,User.class);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
    }

    @RequestMapping("/test8.do")
    public void test8(@RequestBody User user){
        //前端现在传递的是json数据的话，那么后端需要使用@ReuquestBody
        System.out.println(user.getUsername());
        //java对象我们可以直接用
        //如果传过来的是json对象  那么需要去序列化成java对象 否则无法使用啊
        String s = JSONObject.toJSONString(user);
        User parse = JSONObject.parseObject(s,User.class);
        System.out.println(parse.getUsername());
    }

    /**
     * 前端传递一个po类型
     */
    @RequestMapping("/test9.do")
    public void test9(User user){

    }


}
