package com.autumn.base;

import com.autumn.common.SystemConstant;
import com.autumn.exception.custom.CommonException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExceptionController {

    @RequestMapping(SystemConstant.ERROR_CONTROLLER_PATH)
    public void handleException(HttpServletRequest request){
        throw (CommonException) request.getAttribute("auth.error");
    }
}