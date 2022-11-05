package com.bt.constant;

public class CaptchaType {

    public static final int REGISTER_PHONE_EAMIL = 2; //2：邮箱注册验证

    public static final int FORGET_PHONE_EMAIL = 4; //4：手机或者邮箱找回密码

    public static final int MODIFY_PHONE_EMAIL_GOOGLE = 6; //6 修改密码 手机或者邮箱

    public static final int BINGDING_EAMIL_PHONE_GOOGLE = 8; //8 绑定邮箱或者手机号或者谷歌

    public static final int FORGET_ASSET_PHONE = 10;// 找回资金密码

    public static final int MODIFY_POOL_ADDRESS = 12; // 修改支付密码-邮箱验证

    public static final int WITHDRAW_CURRENCY = 13;// 提币

    public static final int MALL_CONSUMPTION = 14;// 商城消费

    public static final int MODIFY_AUTH_SETTING = 15;// 修改安全设置


    //    默认锁定时间五分钟
    public static final Integer LOCK_TIME = 30;


}

