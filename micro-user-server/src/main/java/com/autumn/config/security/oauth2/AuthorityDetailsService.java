package com.autumn.config.security.oauth2;


import com.autumn.config.security.exception.ExceptionEnum;
import com.autumn.config.security.integration.IntegrationAuthentication;
import com.autumn.config.security.integration.IntegrationAuthenticationContext;
import com.autumn.config.security.integration.authenticator.IntegrationAuthenticator;
import com.autumn.domain.user.MallUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class AuthorityDetailsService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    private static List<IntegrationAuthenticator> authenticators;

    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
//        if(true){
//            MallUserDetails mallUserDetails = new MallUserDetails();
//            mallUserDetails.setLoginName("admin");
//            mallUserDetails.setPassword(passwordEncoder.encode("111111"));
//            mallUserDetails.setAccountNonExpired(true);
//            mallUserDetails.setAccountNonLocked(true);
//            mallUserDetails.setCredentialsNonExpired(true);
//            mallUserDetails.setEnabled(true);
//            return mallUserDetails;
//        }

        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        //判断是否是集成登录
        if (integrationAuthentication == null) {
            integrationAuthentication = new IntegrationAuthentication();
        }
        integrationAuthentication.setUsername(username);
        MallUserDetails securityUserinfo = this.authenticate(integrationAuthentication);
        if (StringUtils.isNotBlank(integrationAuthentication.getErrorMsg())) {
            throw new BadCredentialsException(integrationAuthentication.getErrorMsg());
        }

        if (securityUserinfo == null) {
            throw new BadCredentialsException(ExceptionEnum.USER_PASSWORD_ERR.getValue());
        }

        return securityUserinfo;

    }


    /**
     * 使用合适的认证处理器认证
     * @param integrationAuthentication
     * @return
     */
    private MallUserDetails authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    return authenticator.authenticate(integrationAuthentication);
                }
            }
        }
        return null;
    }
}
