package com.bt.constant;

import lombok.Data;

/**
 * 错误码统一五位数字，最高位->最低位 依次表示错误来源
 * 最高位 错误来源 1、来源于用户 2、错误来源于系统 3、错误来源于第二、三方系统
 * 次高位 模块编号 1、wallet-api 2、wallet-task 3、wallet-ha
 * 第三位 各个控制层编号
 * 第四位 service 编号
 **/


public enum ErrorStatus {

    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),

    ADMIN_NOT_ROLE(9000, "无角色信息"),

    ADMIN_WITHDRAW_NOTEXIST(9001, "提币记录不存在"),

    ADMIN_HANDLE_ALREADY(9002, "已经处理,无需重复处理"),

    ADMIN_TRANSACTION_NOTEXIT(9003, "交易信息不存在"),

    ADMIN_NOTEXIST(9004, "Admin信息不存在"),

    SYSTEM_MAINTENANCE(10002, "系统维护中,稍后重试"),

    USER_NOT_FOUND_ERROR(10010, "请输入绑定邮箱/手机号"),

    COMMON_CAPTCHA_ERROR(10011, "请输入正确的验证码"),

    USER_NEED_LOGIN_ERROR(100010, "登录信息已失效,请重新登录"),

    USER_CONSENSUS_FROZEN(100012, "系统维护中,请稍后再试"),

    USER_INVITATIONCODE_NOTEXIST(10013, "邀请码不存在"),

    COMMON_GOOGLE_AUTH_ERROR(10014, "请输入正确的谷歌验证码"),

    COMMON_ASSET_AUTH_ERROR(10015, "请输入正确的资金密码"),

    COMMON_CAPTCHA_INVALID_ERROR(11203, "验证码已失效,请重新发送"),

    COMMON_CAPTCHA_SEND_ALREADY(11204, "验证码已经发送,稍后重试"),

    COMMON_LEAST_OPEN_TWOAUTH(11205, "至少设置一种验证方式"),

    GOOGLEAUTH_BINDALREADY(11206, "谷歌验证已经绑定,无需重复绑定"),

    GOOGLEAUTH_CODEERROR(11207, "谷歌验证码错误"),

    PARAMTER_ERROR(20000, "参数错误"),

    COMMON_ERROR(20001, "网络繁忙，请稍后重试"),

    COIN_NOTEXIST(20002, "币种信息不存在"),

    INSURE_PROJECT_NOTEXIST(20003, "理财保险项目不存在"),

    LESS_THAN_LOWEST(20004, "超过最低理财金额"),

    USER_ORDER_NOTEXIST(20005, "用户订单不存在"),

    LUCKTIME_NOT_ARRIVE(20006, "锁定时间未结束,请稍等"),

    ORDER_LIQUIDATE_ALREADY(20007, "订单已经清算完成"),

    USER_NEED_FROZEN(30000, "账号已经冻结,请联系客服处理"),

    USER_PASSWORD_ERROR(30001, "用户名或密码错误!"),

    PHONE_MOBILE_INCORRECT(30002, "邮箱或者手机号不正确!"),

    USER_NOT_EXIST(30003, "用户信息不存在!"),

    FORGET_PASSWD_MORETIME(30004, "错误次数超过五次,一个小时后重试"),

    PASSWD_MISMATCH(30005, "用户密码不匹配!"),

    TOKEN_EXPIRES(30006, "登录信息已失效,请重新登录!"),

    USER_EXIST_ALREADY(30008, "用户已经存在!"),

    PRODUCT_NOT_EXIST(40000, "理财产品不存在!"),

    PRODUCT_BELOW_MINIMUM(40001, "低于最低数量!"),

    PRODUCT_ABOVE_MAXIMUM(40002, "高于最高数量!"),

    PRODUCT_MORE_BUYLIMIT(40003, "高于最高数量!"),

    GIFTCODE_NOTEXIST(40004, "礼品码不存在!"),

    GIFTCODE_EXCHANGE_ALREADY(40005, "已经兑换!"),

    WITHDRAW_ADDRESS_INCORRECT(40006, "提币地址格式错误!"),

    WITHDRAW_ADDRESS_INVALID(40007, "地址已经失效!"),

    WITHDRAW_ADDRESS_TOOSMALL(40008, "提币低于最低提币数量!"),

    WITHDRAW_ADDRESS_TOOSLARGE(40009, "提币大于最大提币数量!"),

    WALLET_SYSTEM_MAINTENANCE(40010, "钱包系统维护中!"),

    USER_ASSET_NOTENOUGH(40011, "资产不足"),


    ;

    private final int value;

    private final String message;


    ErrorStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }


    /**
     * Return the integer value of this status code.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getMessage() {
        return this.message;
    }
}

