package com.autumn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @Description: https://juejin.cn/post/6888258941378428941
 * 解决主页无法访问的问题  http://127.0.0.1:10761/swagger-ui/index.html
 * @author: jlm
 * @date: 2020/7/2 10:36
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig  extends WebMvcConfigurationSupport {

    @Value("${swagger.show}")
    private boolean swaggerShow ;

    @Bean
    public Docket createOauthRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(SecurityContext.builder()
                        .securityReferences(Arrays.asList(SecurityReference.builder()
                                .reference("Authorization")
                                .scopes(new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})
                                .build()))
                        .build()))
                .securitySchemes(Arrays.asList(new ApiKey("Authorization", "Authorization", "header")))
                .enable(swaggerShow)
//                .groupName("授权接口")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.autumn.controller"))
                .paths(regex("/api/.*/.*"))
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API")
                .description("haha")
                .termsOfServiceUrl("")
                .contact(new Contact("qq", "", ""))
                .version("2.0")
                .build();
    }
}
