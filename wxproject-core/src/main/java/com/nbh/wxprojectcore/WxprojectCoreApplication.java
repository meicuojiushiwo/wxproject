package com.nbh.wxprojectcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource(value="classpath:application-core.properties")
public class WxprojectCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxprojectCoreApplication.class, args);
    }

}

