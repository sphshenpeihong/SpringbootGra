package com.shen.ssmschoolshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching  //支持缓存
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60) //60秒
@MapperScan("com.shen.ssmschoolshop.dao")
public class SpringbootSchoolShopApplication extends SpringBootServletInitializer {

    //上面增加了开启缓存注解，直接启动即可打开redis容器
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootSchoolShopApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSchoolShopApplication.class, args);
    }

    //Springboot配置session过期时间 +redis    redis+session 主要是为了集群共享session作考虑
    //启动redis
    /*public static void main*/

}
