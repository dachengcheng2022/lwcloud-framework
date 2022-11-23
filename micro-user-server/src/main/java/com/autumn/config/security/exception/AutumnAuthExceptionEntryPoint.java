package com.autumn.config.security.exception;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 配置资源服务异常解析
 * @author Administrator
 */
@Component
@Slf4j
public class AutumnAuthExceptionEntryPoint implements AuthenticationEntryPoint {



	@Override
	public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		log.error("{},{}",request.getRequestURI(), authException);
		response.setContentType("application/json;charset=UTF-8");
		String accessToken = request.getHeader("Authorization");
		

		
//		if (authException.getMessage().indexOf("")) {
//			retBiz.setError(ExceptionEnum.USER_OFFLINE.getCodeStr());
//			retBiz.setCode(ExceptionEnum.USER_OFFLINE.getCode());
//		}
		if (authException instanceof InsufficientAuthenticationException) {
	//		retBiz.setError(ExceptionEnum.TOKEN_EXPIRES.getValue());
//			retBiz.setCode(ExceptionEnum.TOKEN_EXPIRES.getCode());
		} else {
//			retBiz.setError(ExceptionEnum.OTHER_CODE.getValue());
//			retBiz.setCode(ExceptionEnum.OTHER_CODE.getCode());
		}
		
		try {
			if (StringUtils.isNotEmpty(accessToken)) {
				String token = accessToken.split(" ")[1];
			/*	if (redisComponent.opsForByteValue("ACCESS_RECORD:"+token) != null) {//还在30天有效期内
//					retBiz.setError(ExceptionEnum.USER_OFFLINE.getValue());
//					retBiz.setCode(ExceptionEnum.USER_OFFLINE.getCode());
				}*/
			}
		} catch (Exception e) {
			log.error("获取用户是否踢出异常:"+e.getMessage(), e);
		}
		
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(JSONObject.toJSONString("认证失败"));
	}
}