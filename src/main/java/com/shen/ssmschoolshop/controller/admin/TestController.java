package com.shen.ssmschoolshop.controller.admin;

import com.shen.ssmschoolshop.entity.Admin;
import org.apache.jasper.tagplugins.jstl.core.Remove;
import org.junit.Test;
import org.springframework.aop.target.PoolingConfig;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Shen Peihong on 2020/4/9 14:21
 */
@Controller
public class TestController{

    public static void main(String[] args) {
        Map<String,Admin> map = new HashMap<String,Admin>();
        Admin admin = new Admin();
        map.put("test",admin);
        admin.setPassword("123");
        System.out.println(map.get("test").getPassword());

    }

    @RequestMapping(path = {"/Test"})
    public String Test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //request的方法
        /*
        System.out.println("getRequestURL:"+request.getRequestURL()); //获取该次请求的URL全路径
        System.out.println("getRequestURI:"+request.getRequestURI());  //获取该请求的相对路径
        System.out.println("getHeader:"+request.getHeader("User-Agent")); //获取该请求的请求头中的User-Agent
        System.out.println("getQueryString:"+request.getQueryString()); //获得该请求的请求参数部分key=value
        System.out.println("getContextPath:"+request.getContextPath()); //获得相对路径
        System.out.println("getMethod:"+request.getMethod());  //获得请求的类型
        System.out.println("getRequestedSessionId:"+request.getRequestedSessionId());  //获得存储在前端Cookie中的SessionID 若没有的话，系统会自动生成一个 然后响应到前端的Cookie中
        System.out.println("getRemoteUser:"+request.getRemoteUser());
        System.out.println("getAuthType:"+request.getAuthType());
        System.out.println("getPathInfo:"+request.getPathInfo());
        System.out.println("getRemoteHost:"+request.getRemoteHost());
        */
        //获取请求的session session的方法
        /*
        HttpSession session = request.getSession();
        System.out.println("session为:"+session); //session在JVM内存中的地址值
        System.out.println("getId:"+session.getId()); //该session的成员变量ID的值
        System.out.println("getLastAccessedTime:"+session.getLastAccessedTime()); //
        System.out.println("getCreationTime:"+session.getCreationTime()); //返回session的创建时间
        System.out.println("getMaxInactiveInterval:"+session.getMaxInactiveInterval()); //有效时间 单位是s 默认是30分钟
        System.out.println("getServletContext:"+session.getServletContext());
        System.out.println("isNew:"+session.isNew()); //判断是否是刚生成的(新用户)
        session.invalidate(); //清空session容器中
         */

        //response
        /*response.setHeader("head","123");
        System.out.println(response.getHeader("Content-Length"));*/
        //response.setDateHeader("Expires");
        //response.sendRedirect("test.html"); //会自动加上前面的路径
        /*
        String userCookie = UUID.randomUUID().toString().trim(); //随机生成Cookie的值
        Cookie cookie = new Cookie("userCookie",userCookie); //创建Cookie对象
        //给Cookie设置有效时间，单位是秒
        // cookie的maxAge属性的默认值是-1：只在浏览器内存存活，关闭浏览器Cookie就消失
        // 大于0：浏览器会把Cookie保存到本地硬盘中，就算关闭浏览器，就算重启客户端电脑，也可以访问服务器
        cookie.setMaxAge(60);
        response.addCookie(cookie);//将Cookie添加到响应头
        */
        return "Test1";
    }

    /**
     * 使用redis的String类型存储结构
     */
    @Test
    public void JedisString(){
        //下面我们将使用我们的Java代码访问redis服务器，需要先跟redis服务器取得连接，才能进一步对redis数据库进行操作
        //步骤和使用JDBC连接mysql数据库一样，需要先建立连接，建立完连接后，对数据库进行操作，最后释放连接
        //后面使用对象池，对连接对象进行管理，默认初始化几百个或几千个连接对象，不用每次都自己去使用构造方法创建连接对象
        //这种方法类似Spring容器的IOC(控制反转)机制
        //1、新建Jedis连接
        Jedis jedis = new Jedis("localhost",6379);
        //2、对redis数据库进行操作
        jedis.del("username1");
        jedis.set("username", "zhangsan");
        jedis.setex("vertifyCode", 60, "675420");//key/value有效时间60秒
        System.out.println(jedis.get("username"));
        //第一次运行代码的话 下面会打印null，因为还没有入redis库
        System.out.println(jedis.get("password"));
        jedis.set("password", "bugaosuni");
        //3、释放连接
        jedis.close();

    }

    /**
     * 使用redis的hashMap存储结构
     */
    @Test
    public void jedisHashmap(){
        Jedis jedis = new Jedis();
        jedis.hset("hashMap1", "username", "zhangsan");
        jedis.hset("hashMap1", "password", "123456");
        System.out.println(jedis.hget("hashMap1", "username"));
        Map<String, String> hashMap1 = jedis.hgetAll("hashMap1");//已获得redis数据库该key对应的Map类型
        //获得map中的所有key集合
        Set<String> keySet = hashMap1.keySet();
        //获得map中的每一对映射关系
        Set<Map.Entry<String, String>> entrySet = hashMap1.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        jedis.close();
    }

    /**
     * 使用redis的list类型存储结构
     */
    @Test
    public void jedisList(){
        Jedis jedis = new Jedis();
        //类似于队列
        jedis.lpush("list1", "a");
        jedis.lpush("list1", "b");
        jedis.rpush("list1", "c");
        System.out.println(jedis.lrange("list1", 0, 2));
        System.out.println(jedis.lpop("list1"));//删除最左边一个元素
        System.out.println(jedis.lrange("list1", 0, -1));
        jedis.close();
    }

    /**
     * 使用redis的set类型存储结构
     */
    @Test
    public void jedisSet(){
        Jedis jedis = new Jedis();
        jedis.sadd("set1", "a");
        jedis.sadd("set1", "b");
        jedis.sadd("set1", "c");
        System.out.println(jedis);
        Set<String> set1 = jedis.smembers("set1");
        System.out.println(set1);

    }

    /**
     * Jedis自带连接池JedisPool
     */
    @Test
    public void JedisPool1(){
        //redis连接池配置对象
        JedisPoolConfig poolingConfig = new JedisPoolConfig();
        poolingConfig.setMaxTotal(50);//最大连接数
        poolingConfig.setMaxIdle(5);//最大闲时数
        //1、创建jedis连接池对象 可以使用有参的jedis连接池对象 增加jedis连接池的配置
        JedisPool jedisPool = new JedisPool(poolingConfig, "127.0.0.1", 6379);
        //2、从jedis连接池中获取jedis对象
        Jedis jedis = jedisPool.getResource();
        //3、使用
        jedis.set("username", "zhangsan");
        System.out.println(jedis.get("username"));
        //4、归还给jedis连接池
        jedis.close();
    }

    /**
     * 测试Set的retainAll的方法
     */
    @Test
    public void setMethod(){
        List<String> list = new ArrayList<String>();
        list.add("111");
        list.add("222");
        list.add("333");
        Set<String> set = new HashSet<String>();
        set.add("111");
        set.add("222");
        set.add("333");
        set.add("444");
        set.retainAll(list);
        System.out.println(set);
        System.out.println(list);
    }

}
