package com.bt.mapper.wallet;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bt.wallet.domain.coin.Tcoininfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TwalletMapper extends BaseMapper<Tcoininfo> {

}