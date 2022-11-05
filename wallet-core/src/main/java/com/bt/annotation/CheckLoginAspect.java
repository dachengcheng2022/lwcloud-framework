/*
 * Copyright (c) 2016. ttp kevin.
 */

package com.bt.annotation;

import com.bt.base.OauthBaseController;
import com.bt.common.RetBiz;
import com.bt.constant.ErrorStatus;
import com.bt.wallet.domain.user.MallUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.util.Arrays;
import java.util.Optional;

/**
 * 切点类
 */
@Aspect
@Component
@Slf4j
public class CheckLoginAspect {

    /**
     *
     */
    @Around(value = "@annotation(com.bt.annotation.CheckLogin)")
    public RetBiz checkLogin(ProceedingJoinPoint proceedingJoinPoint) {
        Object target = proceedingJoinPoint.getThis();
        RetBiz retBiz = new RetBiz();
        if (target instanceof OauthBaseController) {
            MallUser securityUserinfo = ((OauthBaseController) target).getSecurityUserinfo();
            if (securityUserinfo == null) {
                retBiz.setError("token失效");
                return retBiz;
            }

            if (securityUserinfo.getDeleted()) {
                retBiz.setError(ErrorStatus.USER_NEED_FROZEN.getMessage());
                retBiz.setCode(ErrorStatus.USER_NEED_FROZEN.getValue());
                return retBiz;
            }

            try {
                retBiz = (RetBiz) proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                log.error("Throwable {}", throwable);
                retBiz.setError("系统内部错误");
            }
        }
        return retBiz;
    }

    private String parsURL(String[] classRequestMapping, String[] methdRequestMapping) {
        StringBuffer sb = new StringBuffer();
        for (String str : classRequestMapping) {
            sb.append(str);
        }

        for (String str : methdRequestMapping) {
            sb.append(str);
        }
        return sb.toString();
    }

    private String parsArgs(Object[] args) {
        StringBuffer sb = new StringBuffer();
        Optional.ofNullable(args).ifPresent(v -> Arrays.stream(v).forEach(arg -> {
            if (!(arg instanceof ServletRequest)) {
                sb.append(arg.toString());
            }
        }));
        return sb.toString();
    }


}