package com.autumn.service.user;

import com.autumn.domain.user.MallUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jlm
 * @since 2022-12-15
 */
public interface MallUserService extends IService<MallUser> {
    MallUser selectByEmail(String username);

    MallUser selectByPhone(String username);
}
