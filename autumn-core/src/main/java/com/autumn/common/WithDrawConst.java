package com.autumn.common;/**
 * Created by Administrator on 2018/10/15.
 */

public class WithDrawConst {
    public static final int STATUS_TO_BE_AUDIT = 0;//待审核
    public static final int STATUS_PASS_AUDITED = 1;//审核通过
    public static final int STATUS_FAILED_AUDITED = 2;//审核失败

    public static final int BIZ_TYPE_ACTIVITY_REWARD = 1;//活动奖励
    public static final int BIZ_TYPE_MIRCO_WITHDRAW = 2;//微账户提现

    public enum PublishCoinStatus{
        //0-待发币 1-发币中 2-发币失败 3-发币成功
        UNPUBULISHED(0),PUBLISH_NOW(1),PUBLISH_FAIL(2),PUBLISH_SUCCESS(3);
        PublishCoinStatus(int code){
            this.code = code;
        }
        private int code;

        public int getCode() {
            return code;
        }
    }


}
