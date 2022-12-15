//package com.autumn.config.security.integration.authenticator;
//
//
//import com.autumn.config.security.exception.ExceptionEnum;
//import com.autumn.config.security.integration.AbstractPreparableIntegrationAuthenticator;
//import com.autumn.config.security.integration.IntegrationAuthentication;
//import com.autumn.domain.user.MallUser;
//import com.autumn.domain.user.MallUserDetails;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//
///**
// * 默认登录处理
// *
// * @author LIQIU
// * @date 2018-3-31
// **/
//@Component
//@Primary
//@Slf4j
//public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {
//
//
//    @Resource
//    private IUserService iUserService;
//
//    @Override
//    public MallUserDetails authenticate(IntegrationAuthentication integrationAuthentication) {
//        log.info("进入默认登录处理器");
//        String username = integrationAuthentication.getUsername();
//        MallUser tuserinfo = null;
//        if (username.contains("@")) {
//            tuserinfo = this.iUserService.selectByEmail(username);
//        } else {
//            tuserinfo = this.iUserService.selectByPhone(username);
//        }
//        if (tuserinfo == null) {
//            integrationAuthentication.setErrorMsg(ExceptionEnum.USER_PASSWORD_ERR.getValue());
//            return null;
//        } else {
//            if (StringUtils.isBlank(tuserinfo.getPassword())) {
//                integrationAuthentication.setErrorMsg(ExceptionEnum.USER_PASSWORD_ERR.getCodeStr());
//                return null;
//            }
//        }
//        if (this.checkLogin(tuserinfo)) {
//            throw new AccountFrozenException(ExceptionEnum.USER_FROZEN.getCodeStr());
//        }
////        MallUserDetails securityUserinfo = new MallUserDetails(tuserinfo, username);
//        MallUserDetails securityUserinfo = new MallUserDetails(tuserinfo, username);
//        securityUserinfo.setAuthorities(authorities);
//
//        String clientIp = integrationAuthentication.getAuthParameter("reqIp");
//        if (StringUtils.isNotEmpty(clientIp)) {
//            if (clientIp.indexOf(",") > -1) {
//                clientIp = clientIp.substring(0, clientIp.indexOf(","));
//            }
//            // redisComponent.opsForHashDelete(RedisPrefix.getUserPswLoginErrCountKey(), clientIp);
//        }
//
//        return securityUserinfo;
//    }
//
//    @Override
//    public void prepare(IntegrationAuthentication integrationAuthentication) {
//
//    }
//
//    @Override
//    public boolean support(IntegrationAuthentication integrationAuthentication) {
//        return StringUtils.isEmpty(integrationAuthentication.getAuthType());
//    }
//
//
//}
