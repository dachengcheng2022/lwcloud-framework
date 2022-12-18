package com.autumn.service.user.impl;

import com.autumn.domain.user.MallUser;
import com.autumn.mapper.user.MallUserMapper;
import com.autumn.service.user.MallUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jlm
 * @since 2022-12-15
 */
@Service
public class MallUserServiceImpl extends ServiceImpl<MallUserMapper, MallUser> implements MallUserService {
    @Override
    public MallUser selectByEmail(String email) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email", email);
        MallUser user = baseMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public MallUser selectByPhone(String mobile) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("mobile", mobile);
        MallUser user = baseMapper.selectOne(wrapper);
        return user;
    }

}
