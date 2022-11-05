package com.bt.base;

import com.bt.constant.ErrorStatus;
import com.bt.exception.AuthenticationFailedException;
import com.bt.wallet.domain.user.MallUser;
import com.bt.wallet.mapper.CommonMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OauthBaseController extends BaseController {
    @Resource
    private CommonMapper commonMapper;

    public MallUser getSecurityUserinfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof MallUser)) {
            return null;
        }
        //根据token获取用户信息
        MallUser mallUser = (MallUser) authentication.getPrincipal();
        Boolean aBoolean = commonMapper.selectIsDelete(mallUser.getId());
        mallUser.setDeleted(aBoolean);
        if (aBoolean == null) {
            return null;
        }

        return mallUser;
    }

    public Integer getUserId() {
        MallUser user = getSecurityUserinfo();
        if (user == null || user.getId() == null) {
            throw new AuthenticationFailedException(ErrorStatus.USER_NEED_LOGIN_ERROR.getMessage());
        }
        if (user.getDeleted()) {
            throw new AuthenticationFailedException(ErrorStatus.USER_NEED_FROZEN.getMessage());
        }
        return user.getId();
    }
}
