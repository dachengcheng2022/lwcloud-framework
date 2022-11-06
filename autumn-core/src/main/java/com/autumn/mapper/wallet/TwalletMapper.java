package com.autumn.mapper.wallet;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.autumn.wallet.domain.coin.Tcoininfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TwalletMapper extends BaseMapper<Tcoininfo> {

}