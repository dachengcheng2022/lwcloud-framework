package com.bt.config.aspect;

import com.bt.common.RetBiz;
import com.bt.constant.ErrorStatus;
import com.bt.exception.custom.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle {


    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = CommonException.class)
    public RetBiz handleCommon(CommonException e) {
        RetBiz retBiz = new RetBiz();
        String localeMessage = getLocaleMessage(e.getErrorCode());
        retBiz.setError(localeMessage);
        retBiz.setCode(e.getErrorCode());
        log.error("Exception {} ", e.getMessage());
        return retBiz;
    }


    @ExceptionHandler(value = {RuntimeException.class})
    public RetBiz defaultErrorHandler(RuntimeException exception) {
        log.error("Exception:  ", exception);
        String localeMessage = getLocaleMessage(ErrorStatus.COMMON_ERROR.getValue());
        RetBiz retBiz = new RetBiz();
        retBiz.setError(localeMessage);
        retBiz.setCode(ErrorStatus.COMMON_ERROR.getValue());
        return retBiz;
    }

    /**
     * 异常全局处理
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {Exception.class})
    public RetBiz constraintViolationException(Exception exception) {
//        if (exception instanceof MethodArgumentNotValidException) {
//            BindingResult bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
//            if (bindingResult.hasErrors()) {
//                log.error("Exception {}", exception.getMessage());
//                return RetBiz.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
//            }
//        }
        log.error("{}",exception);
        String localeMessage = getLocaleMessage(ErrorStatus.PARAMTER_ERROR.getValue());
        RetBiz retBiz = new RetBiz();
        retBiz.setError(localeMessage);
        retBiz.setCode(ErrorStatus.PARAMTER_ERROR.getValue());
        return retBiz;
    }

    private String getLocaleMessage(int code) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage("" + code, null, locale);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("unknown error code:{},code");
            return "error code:" + code;
        }
    }
}
