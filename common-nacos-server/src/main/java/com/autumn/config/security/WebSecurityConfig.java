//package com.mall.config.security;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/fonts/**", "/font/**", "/plugins/**", "/img/**", "/webjars/**",
//                        "/ssadmin/**", "/**.htmls", "/oauth/**", "/mobile/**", "/swagger-ui.html**").permitAll()
//                .antMatchers("/**").permitAll()
//                .and()
//                .httpBasic().disable()
//                .csrf().disable();
////                .addFilterAfter(new UserRoleFilter(),SecurityContextHolderAwareRequestFilter.class);
//    }
//
//
//}