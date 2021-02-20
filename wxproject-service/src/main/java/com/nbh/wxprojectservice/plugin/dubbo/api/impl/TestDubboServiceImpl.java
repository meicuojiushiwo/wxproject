package com.nbh.wxprojectservice.plugin.dubbo.api.impl;

import com.nbh.wxprojectserviceapi.plugin.dubbo.api.TestDubboService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class TestDubboServiceImpl implements TestDubboService {

    @Override
    public String sayHi(String name) {
        return "hello," + name;
    }
}
