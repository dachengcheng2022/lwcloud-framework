package com.autumn.wallet.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommonMapper {

    @Select({
            "select",
            "tsavalue",
            "from system_config",
            "where tsakey = #{key,jdbcType=VARCHAR}",
    })
    String selectConfig(String key);

    @Select({
            "select",
            "deleted",
            "from mall_user",
            "where id = #{userid,jdbcType=INTEGER}",
    })
    Boolean selectIsDelete(Integer userid);
}