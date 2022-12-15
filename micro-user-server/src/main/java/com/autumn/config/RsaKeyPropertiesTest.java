//package com.autumn.config;
//
//import com.autumn.utils.security.RSAUtils;
//import com.nimbusds.jose.jwk.RSAKey;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.stereotype.Component;
//
///**
// * <p>
// * RsaKeyProperties
// * </p>
// *
// * @author livk
// */
//@Configuration
//public class RsaKeyPropertiesTest {
//
//
//    @Value("${swagger.show}")
//    private boolean swaggerShow ;
//
//    @Value("${rsa.key.jks.location}")
//    private String location;
//
//
//    @Bean
//    public Object show(){
//        System.err.println(location);
//        return new Object();
//    }
//
//    public RsaKeyPropertiesTest() {
//        System.err.println(swaggerShow);
//        Resource jksResource = resourceResolver.getResource("classpath:/jwt.jks");
//        rsaKey = RSAUtils.rsaKey(jksResource, "123456", "jwt");
//    }
//}
