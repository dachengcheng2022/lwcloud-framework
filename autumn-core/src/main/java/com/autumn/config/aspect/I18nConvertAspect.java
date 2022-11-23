///*
// * Copyright (c) 2016. ttp kevin.
// */
//
//package com.autumn.config.aspect;
//
//import com.autumn.common.RetBiz;
//import com.autumn.wallet.domain.admin.MallAdmin;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletRequest;
//import java.util.Arrays;
//import java.util.Locale;
//import java.util.Optional;
//
///**
// * 切点类
// */
//@Aspect
//@Component
//@Slf4j
//public class I18nConvertAspect {
//
//
//    @Autowired
//    private MessageSource messageSource;
//
//    @Pointcut("execution(public *  com.autumn.wallet.controller..*.*(..))")
//    public void anyController() {
//    }
//
//
//    @Around("anyController()")
//    public RetBiz i18nConvert(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        RetBiz retBiz = new RetBiz();
//        retBiz = (RetBiz) proceedingJoinPoint.proceed();
//        if (StringUtils.isNotEmpty(retBiz.getError())) {
//            if (retBiz.getCode() != null && retBiz.getCode() != -1) {
//                retBiz.setError(getLocaleMessage(retBiz.getCode()));
//            } else {
//                log.warn("未国际化的语句:{}", retBiz.getError());
//            }
//        }
//        return retBiz;
//    }
//
//    /**
//     * 获取国际化异常信息
//     */
//    private String getLocaleMessage(int code) {
//        try {
//            Locale locale = LocaleContextHolder.getLocale();
//            return messageSource.getMessage("" + code, null, locale);
//        } catch (Exception e) {
//            log.error("unknown error code:{}",e);
//            return "error code:" + code;
//        }
//    }
//}