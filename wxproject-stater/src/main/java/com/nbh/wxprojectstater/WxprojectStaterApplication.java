package com.nbh.wxprojectstater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@PropertySource(value = "classpath:application-core.properties")
public class WxprojectStaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxprojectStaterApplication.class, args);
    }

}

