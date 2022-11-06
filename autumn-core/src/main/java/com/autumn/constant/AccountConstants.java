package com.autumn.constant;

public class AccountConstants {

    //    用户激活
    public final static Integer USER_ACTIVATE = 1;

    //    用户未激活
    public final static Integer USER_UN_ACTIVATE = 0;

    //币币账户
    public final static Integer APPLICATION_FOR_CORE_ACCOUNT = 1;

    //2x外部账户->钱包账户
    public final static Integer WALLET_ACCOUNT = 2;

    /**
     * 3x内部账户
     * 兑换计算次数
     */
    public static final int APPLICATION_FOR_MALLACCOUNT = 10;

//    活动账户 礼品卡
    public static final int APPLICATION_FOR_ACTIVITY= 11;

    //    外部转账交易类型
    public final static Integer TX_TYPE_OUTER = 1;

    //    内部转账交易类型
    public final static Integer TX_TYPE_INNER = 2;

    //    自动提币
    public final static Integer TX_TYPE_AUTOWITHDRAW = 3;

    //    合伙人激励
    public final static Integer TX_TYPE_INNER_SUBSIDY = 21;

    //推荐返佣
    public final static Integer TX_TYPE_INNER_ENCOURAGE = 22;

    //活动奖励
    public final static Integer TX_TYPE_REBATE_AWARD = 35;


    public static final Integer APPLICATION_FOR_MALL_RECOMMEND = 32;

    //系统操作
    public final static Integer APPLICATION_FOR_AIRPUSH = 99;
}
