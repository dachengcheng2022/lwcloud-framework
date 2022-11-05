package com.bt.common;

public class TxType {
    public static final int TX_TYPE_SEND =1;
    public static final int TX_TYPE_RECEIVE =2;
    public static final int TX_TYPE_DPOS_REGISTER = 3;
    public static final int TX_TYPE_DPOS_VOTE = 4;
    public static final int TX_TYPE_DPOS_CANCELVOTE = 5;
    public static final String DACRS_TX_TYPE_COMMON = "COMMON_TX";/**交易方式:普通交易*/
    public static final String DACRS_TX_TYPE_CONTRACT = "CONTRACT_TX";/**交易方式:合约*/
    public static final String DACRS_TX_TYPE_REGISTER = "REG_ACCT_TX"; /**交易方式:激活*/


}
