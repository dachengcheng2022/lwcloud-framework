package com.autumn.config.security.integration;


import com.autumn.config.security.integration.authenticator.IntegrationAuthenticator;
import com.autumn.domain.user.MallUser;
import com.autumn.domain.user.MallUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public abstract class AbstractPreparableIntegrationAuthenticator implements IntegrationAuthenticator {

    protected static final long ERROR_LOGIN_EXPIRE_TIME = 1 * 60 * 60 * 1000L;
    protected static List<SimpleGrantedAuthority> authorities = new ArrayList() {{
        add(new SimpleGrantedAuthority("ROLE_REST"));
    }};

    @Override
    public abstract MallUserDetails authenticate(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract void prepare(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract boolean support(IntegrationAuthentication integrationAuthentication);

    @Override
    public void complete(IntegrationAuthentication integrationAuthentication) {

    }

    protected boolean checkLogin(MallUser mallUser) {
        boolean flag = false;
        long curTime = System.currentTimeMillis();
        log.info("check login");
//        if (mallUser.getUisfrozen()) {
//            Tuserinfo tuserinfo1 = new Tuserinfo();
//            tuserinfo1.setUserid(mallUser.getUserid());
//            boolean relieveFlag = false;
//            if (mallUser.getUisfrozen()) {
//                if (mallUser.getUfronzentime() != null) {
//                    long ftime = mallUser.getUfronzentime().getTime();
//                    if (curTime - ftime > ERROR_LOGIN_EXPIRE_TIME) {
//                        relieveFlag = true;
//                    } else {
//                        flag = true;
//                    }
//                } else {
//                    relieveFlag = true;
//                }
//            }
//            if (relieveFlag) {
//                tuserinfo1.setUisfrozen(Boolean.FALSE);
//                tuserinfo1.setUloginerrcount(0);
//                mallUser.setUisfrozen(Boolean.FALSE);
//                mallUser.setUloginerrcount(0);
////                this.tuserinfoService.updateByPrimaryKeySelective(tuserinfo1);
//            }
//        }
        return flag;
    }
}
