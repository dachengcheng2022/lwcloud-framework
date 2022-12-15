//package com.autumn.config;
//
//import com.autumn.utils.security.RSAUtils;
//import com.nimbusds.jose.jwk.RSAKey;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.bind.Name;
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
////@ConfigurationProperties(RsaKeyProperties.PREFIX)
//public class RsaKeyProperties {
//
//    public static final String PREFIX = "rsa.key.jks";
//
//    @Value("${rsa.key.jks.location}")
//    private String location;
//
//    @Value("${swagger.show}")
//    private boolean swaggerShow ;
//
//    @Value("${rsa.key.jks.password}")
//    private String password;
//
//    @Value("${rsa.key.jks.alias}")
//    private String alias;
//
//    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
//
////    private final RSAKey rsaKey;
//
////    public RsaKeyProperties(@Name("location") String location,
////                            @Name("password") String password,
////                            @Name("alias") String alias) {
////        Resource jksResource = resourceResolver.getResource(location);
////        rsaKey = RSAUtils.rsaKey(jksResource, password, alias);
////    }
//
//    @Bean
//    public RSAKey rsaKey() {
//        Resource jksResource = resourceResolver.getResource(location);
//        RSAKey rsaKey = RSAUtils.rsaKey(jksResource, password, alias);
//        return rsaKey;
//    }
//}
