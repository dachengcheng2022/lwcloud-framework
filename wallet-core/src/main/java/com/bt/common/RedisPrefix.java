package com.bt.common;

public class RedisPrefix {
    public final static String IP_BLACK_LIST = "IP:BLACK:LIST:";

    public final static String API_CALL_FREQUENCY = "API:CALL:FREQUENCY:";

    public final static String DELEGATE = "DELEGATE_"; // 地址

    public final static String MIRCO_ADDR = "MIRCO_ADDR_"; // 微账户地址

    public final static String COIN_HEIGHT = "COIN_HEIGHT";

    public final static String USER_TOKEN_BALANCE = "UT_BAL_";

    public final static String USER_COIN_BALANCE = "UC_BAL_";

    public final static String MEMPOOL_TX = "MEMPOOL_TX";

    public final static String ADDRESS = "ADDRESS_";

    public final static String CHARGE_UNIT = "CHARGE_UNIT";

    public final static String THIRD_SENDTX_WAIT_SEND_MAP = "THIRD_WAIT_SEND_TX";

    public final static String COIN_PRICE_USD_MAP = "coin_price_used_map";

    public final static String EXCHANGE_RATE_MAP = "exchange_rate_map";

    public final static String THIRD_SENDTX_HASH_MEM_SYMBOLKEY_SET = "third_sendtx_hash_mem_";

    public final static String USER_PAYPWD_ERROR_INFO = "user_paypwd_error_info_";

    public final static String THIRD_SENDTX_MSG = "THIRD_SENDTX_MSG";

    public final static String EXCHANGE_HUOBI_PRICE = "EXCHANGE_HUOBI_PRICE_";

    public final static String TX_CONFIRM_HEIGHT = "TX_CONFIRM_";

    public final static String LOGIN_UPERR_CNT = "LOGIN_UPERR_CNT";

    public final static String USER_LOGIN_TOKEN = "USER_LOGIN_TOKEN";

    public final static String ACTIVITY_REGISTER_IMEI = "ACT_REG_IMEI";

    public final static String ACTIVITY_REGISTER_IP = "ACT_REG_IP";

    public final static String OCR_LIMIT = "OCR_LIMIT:";

//   币种行情
    public final static String COIN_QUOTATION = "_QUOTATION";

    /**
     * key for redis set
     */
    public static String getCoinAddrKey(String symbol) {
        return ADDRESS + symbol;
    }

    /**
     * key for redis set
     */
    public static String getMircoAddrKey(String symbol) {
        return MIRCO_ADDR + symbol;
    }

    /**
     * key for redis set
     */
    public static String getDelegateAddrKey(String symbol) {
        return DELEGATE + symbol;
    }

    /**
     * key for redis hash
     */
    public static String getCoinHeightKey() {
        return COIN_HEIGHT;
    }

    /**
     * key for redis hash
     */
    public static String getChargeUnitKey() {
        return CHARGE_UNIT;
    }

    /**
     * key for redis hash
     */
    public static String getUserCoinBalanceKey(int walletid) {
        return USER_COIN_BALANCE + walletid;
    }

    /**
     * key for redis hash
     */
    public static String getUserTokenBalanceKey(int walletid) {
        return USER_TOKEN_BALANCE + walletid;
    }

    public static String getMempoolTxKey() {
        return MEMPOOL_TX;
    }

    public static String getCoinTxConfirmHeightKey(String symbol) {
        return TX_CONFIRM_HEIGHT + symbol;
    }

    public static String getUserPswLoginErrCountKey() {
        return LOGIN_UPERR_CNT;
    }

    public static String getUserLoginTokenKey() {
        return USER_LOGIN_TOKEN;
    }

    public static String getActivityRegisterImeiKey() {
        return ACTIVITY_REGISTER_IMEI;
    }

    public static String getActivityRegisterIpKey() {
        return ACTIVITY_REGISTER_IP;
    }

    public static String getOcrLimitKey(String day) {
        return OCR_LIMIT + day;
    }

}
