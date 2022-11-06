package com.autumn.constant;

public class WalletConstants {
    public final static Integer TRANSFER_IN_SEND_STATUS_WAIT = 0; //待发送处理记录
    public final static Integer TRANSFER_IN_SEND_STATUS_SUCCESSFUL = 1; //成功
    public final static Integer TRANSFER_IN_SEND_STATUS_FAILED = 2;  //失败

    public final static Integer TRANSFER_IN_STATUS_UNCONFIRMED = 0; //表示正在转账
    public final static Integer TRANSFER_IN_STATUS_SUCCESSFUL = 1; //成功
    public final static Integer TRANSFER_IN_STATUS_FAILED = 2;  //失败

    final public static Integer TRANSFER_OUT_TRANSFERING = 0; // 0 表示正在上链转账
    final public static Integer TRANSFER_OUT_SUCCESSFUL = 1; // 1 表示上链成功（确认）
    final public static Integer TRANSFER_OUT_FAILED = 2; // 2 表示上链失败
    final public static Integer TRANSFER_OUT_WAITING = 3; // 3 待上链转账
    final public static Integer TRANSFER_OUT_OPTING = 5; //
    final public static Integer TRANSFER_OUT_WAITVERIFY = 6; //

    public static final Integer COIN_TYPE_ETH = 2;
    public static final Integer COIN_TYPE_BTC = 1;
    public static final Integer COIN_TYPE_TRX = 3;
    public static final Integer COIN_TYPE_FIL = 4;

    public static final String CURRENCY_ETH = "ETH";
    public static final String CURRENCY_BTC = "BTC";
    public static final String CURRENCY_TOKEN_IDEER = "IDEER";
    public static final String CURRENCY_USDT = "USDT";

}
