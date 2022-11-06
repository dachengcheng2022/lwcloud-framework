package com.autumn.controller;

import com.autumn.RedisComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Resource
    private RedisComponent redisComponent;

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/get")
    public boolean get() {
        redisComponent.setForValue("tesst","22222");
        String s = redisComponent.opsForValue("tesst", "test");
        System.err.println(s);
        return useLocalCache;
    }
}