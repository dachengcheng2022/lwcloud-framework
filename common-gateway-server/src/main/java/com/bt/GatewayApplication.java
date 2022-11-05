package com.bt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 服务网关
 *
 * @author chengen
 * @ClassName: GatewayApplication
 * @date 2017年2月16日
 * @version: V1.0
 */
@SpringBootApplication
@EnableScheduling
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
