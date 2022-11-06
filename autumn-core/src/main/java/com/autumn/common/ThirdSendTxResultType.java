package com.autumn.common;

public class ThirdSendTxResultType {

    public static final String SYSTEMERROR = "SYSTEMERROR";//系统错误	系统超时	系统异常，请用相同参数重新调用
    public static final String DAPPID_NOT_EXIST = "DAPPID_NOT_EXIST";//DAPPID不存在	参数中DAPPID和存储不匹配	请检查DAPPID是否正确
    public static final String LACK_PARAMS = "LACK_PARAMS";//缺少参数	缺少必要的请求参数	请检查参数是否齐全
    public static final String OUT_TRADE_NO_USED = "OUT_TRADE_NO_USED";//商户订单号重复	同一笔交易不能多次提交	请核实商户订单号是否重复提交
    public static final String SIGNERROR = "SIGNERROR";//	签名错误	参数签名结果不正确	请检查签名参数和方法是否都符合签名算法要求
    public static final String NOT_UTF8 = "NOT_UTF8";//编码格式错误	未使用指定编码格式	请使用UTF-8编码格式
    public static final String OTHER_ERROR = "OTHER_ERROR";//未知错误	未知错误	未知错误
    public static final String CHECK_PARAMS_ERROR = "CHECK_PARAMS_ERROR";//校验参数错误	校验参数错误	校验参数错误（非法参数）
}
