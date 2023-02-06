package com.autumn.controller;

import com.autumn.RedisComponent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "测试接口")
public class TestController {

    @Resource
    private RedisComponent redisComponent;

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @ApiOperation(value = "test", httpMethod = "GET", response = String.class, notes = "首页行情")
    @RequestMapping(value = "/test/v1/test", method = RequestMethod.GET)
    public String queryIndexCoins() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "hello";
    }


    @ApiOperation(value = "test2", httpMethod = "GET", response = String.class, notes = "首页行情")
    @RequestMapping(value = "/common/v1/test", method = RequestMethod.GET)
    public String queryIndexCoins2() {
        return "567";
    }

    @ApiOperation(value = "login", httpMethod = "POST", response = String.class, notes = "登陆")
    @RequestMapping(value = "/common/v1/login", method = RequestMethod.POST)
    public String login() {
        return "login";
    }
}