//package com.autumn;
//
//import com.alibaba.nacos.api.NacosFactory;
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.exception.NacosException;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SpringBootApplication.class)
//public class UserServerApplicationTest {
//
//    @Test
//    public void testnacos() throws NacosException {
//        // 访问nacos
//        ConfigService configService = NacosFactory.createConfigService("127.0.0.1:8848");
//// 通过DataId和Group获取配置文件内容
//        String contentInfo = configService.getConfig("user-server-test.yaml","DEFAULT_GROUP",1000L);
//        System.err.println(contentInfo);
//    }
//}