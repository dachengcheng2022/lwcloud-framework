package com.bt.config.aspect;

import com.bt.common.RetBiz;
import com.bt.constant.ErrorStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Locale;


@Component
@Aspect
public class BindingResultAspect {
    @Autowired
    private MessageSource messageSource;

    @Pointcut("execution(public *  com.bt.wallet.controller..*.*(..))")
    public void anyController( ) {
    }


    @Around("anyController() && args(*,bindingResult,..)")
    public Object doBefore(ProceedingJoinPoint joinPoint,BindingResult bindingResult ) throws Throwable {
        if (bindingResult.hasErrors()) {
//            RetBiz retBiz = new RetBiz();
//            FieldError fieldError = bindingResult.getFieldError();
//            if (fieldError != null) {
//                retBiz = new RetBiz();
//                retBiz.setError(fieldError.getDefaultMessage());
//            } else {
//                retBiz.setError("参数异常");
//            }
//            return retBiz;
            String localeMessage = getLocaleMessage(ErrorStatus.PARAMTER_ERROR.getValue());
            RetBiz retBiz = new RetBiz();
            retBiz.setError(localeMessage);
            retBiz.setCode(ErrorStatus.PARAMTER_ERROR.getValue());
            return retBiz;
        }


        return joinPoint.proceed();
    }

    private String getLocaleMessage(int code) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage("" + code, null, locale);
        } catch (Exception e) {
            e.printStackTrace();
            return "error code:" + code;
        }
    }

}
