package com.nbh.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @version 1.0
 * @author bojiangzhou 2018-02-19
 */
@Configuration
@EnableSwagger2Doc
@PropertySource(value = "classpath:application-swagger.properties")
public class WxprojectSwaggerConfig extends WebMvcConfigurerAdapter {
    /**
     * 注入swagger资源文件
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
