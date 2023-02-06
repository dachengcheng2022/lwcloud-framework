package com.autumn.config.security.integration.authenticator;


import com.autumn.config.security.exception.ExceptionEnum;
import com.autumn.config.security.integration.AbstractPreparableIntegrationAuthenticator;
import com.autumn.config.security.integration.IntegrationAuthentication;
import com.autumn.domain.user.MallUser;
import com.autumn.exception.custom.CommonException;
import com.autumn.vo.security.TokenUserDetails;
import com.autumn.service.user.MallUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 默认登录处理
 *
 * @author LIQIU
 * @date 2018-3-31
 **/
@Component
@Primary
@Slf4j
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {


    @Resource
    private MallUserService mallUserService;

    @Override
    public TokenUserDetails authenticate(IntegrationAuthentication integrationAuthentication) {
        log.info("进入默认登录处理器");
        String username = integrationAuthentication.getUsername();
        MallUser tuserinfo = null;
        if (username.contains("@")) {
            tuserinfo = this.mallUserService.selectByEmail(username);
        } else {
            tuserinfo = this.mallUserService.selectByPhone(username);
        }
        if (tuserinfo == null) {
            integrationAuthentication.setErrorMsg(ExceptionEnum.USER_PASSWORD_ERR.getValue());
            return null;
        } else {
            if (StringUtils.isBlank(tuserinfo.getPassword())) {
                integrationAuthentication.setErrorMsg(ExceptionEnum.USER_PASSWORD_ERR.getCodeStr());
                return null;
            }
        }
        if (this.checkLogin(tuserinfo)) {
            throw new CommonException(ExceptionEnum.USER_FROZEN.getCodeStr());
        }
        TokenUserDetails securityUserinfo = new TokenUserDetails(username,tuserinfo.getPassword());
        securityUserinfo.setUserId(tuserinfo.getId());
//        securityUserinfo.setAuthorities(authorities);
        return securityUserinfo;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return StringUtils.isEmpty(integrationAuthentication.getAuthType());
    }


}
