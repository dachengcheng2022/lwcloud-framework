package com.bt.common;/**
 * Created by Administrator on 2018/11/2.
 */

/**
 * @program: xwallet
 * @description:DistributeLockConstant
 * @author: Woody
 * @createtime: 2018-11-02 11:24
 **/
public class DistributeLockConstant {
    //分布式锁过期时间(毫秒)
    public static final long REQUEST_TIME = 15000;

    //分布式环境同时减账户余额全局Key
    public static final String SUBSTRACT_ACCOUNT_ID = "substractAcountId_";

    //分布式环境同时减账户冻结余额全局Key
    public static final String MIRCO_ASSET_THAW = "mircoAssetThaw_";

    //分布式环境同时抢红包全局Key
    public static final String GAIN_LUCK_MONEY_BY_UUID = "gainLuckMoneyByUuid_";

    //分布式环境同时抢红包全局Key
    public static final String SEND_LUCK_MONEY_TO_ACCOUNT = "sendLuckMoneyToAccount_";

    public static final String ADDRESS_REQUEST = "ADDRESS_REQUEST:";

    //分布式环境下 资产锁
    public static final String ASSET_LOCK = "ASSET_LOCK:";

    public static final String MESSAGE_LOCK = "MESSAGE_LOCK:";

    public static final String EXCHANGE_GIFTCODE_LOCK = "EXCHANGE_GIFTCODE_LOCK:";

}
