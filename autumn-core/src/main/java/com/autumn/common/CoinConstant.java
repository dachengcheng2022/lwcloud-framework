package com.autumn.common;/**
 * Created by Administrator on 2018/12/5.
 */

/**
 * @program: xwallet
 * @description:CoinConstant
 * @author: Woody
 * @createtime: 2018-12-05 14:01
 **/
public class CoinConstant {

    //币种唯一索引前缀
    public interface CoinKeyPrefix {
        String IS_TOKEN_TRUE = "1_";//代币前缀
        String IS_TOKEN_FALSE = "0_";//主币前缀
    }

    public static String TOKEN_ICOIN_URL = "https://static.tokenview.com/icon/eth/%s.png";
}
