package com.autumn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @nacos 启动一个服务注册中心提供给其他应用进行对话
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

//    @RestController
//    class EchoController {
//        @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
//        public String echo(@PathVariable String string) {
//            return "Hello Nacos Discovery " + string;
//        }
//    }

}
