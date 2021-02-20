package com.nbh.wxprojectclient.plugin.dubbo.api;

import com.nbh.wxprojectserviceapi.plugin.dubbo.api.TestDubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestDubbo {

    @Resource
    private TestDubboService testDubboService;

    public void test(String name) {
        System.out.println(testDubboService.sayHi(name));
    }

}
