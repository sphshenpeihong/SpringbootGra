package com.shen.ssmschoolshop.util;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Shen Peihong on 2020/4/25 19:05
 */

public class JedisPoolUtils {
    //该工具类最终的目的是为了使用jedis连接池创建jedis对象
    //下面已封装了方法，但是此时的jedisPool对象是null的，需要使用static块来初始化jedisPool
    private static JedisPool jedisPool;

    //使用静态代码块去加载jedis连接池的配置 而不用通过set的方式
    static{
        //获得工具类的类装载器 然后获得resource的配置目录下的properties配置文件 输入流对象已指向该配置文件
        InputStream inputStream = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedisPool.properties");
        //新建Properties对象
        Properties properties = new Properties();
        //装载输入流对象
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //封装配置对象
        GenericObjectPoolConfig jedisPoolConfig = new GenericObjectPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        //初始化jedisPool
        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
    }

    /**
     * 使用jedis连接池的工具类去获取jedis对象
     * @return
     */
    public Jedis getJedis(){
        return jedisPool.getResource();
    }
}
