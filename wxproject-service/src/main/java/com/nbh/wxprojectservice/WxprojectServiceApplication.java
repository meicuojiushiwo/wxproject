package com.nbh.wxprojectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations= {"classpath:com/nbh/wxprojectservice/plugin/dubbo/xml/*.xml"})
public class WxprojectServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxprojectServiceApplication.class, args);
    }

}
