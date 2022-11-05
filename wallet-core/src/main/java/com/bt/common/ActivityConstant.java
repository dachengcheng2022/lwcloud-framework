package com.bt.common;

import java.math.BigDecimal;

public class ActivityConstant {
    //    状态
    public final static int STATUS_TO_BE_RELEASED = 0; //待发布
    public final static int STATUS_PUBLISHED = 1;//已发布
    public final static int STATUS_PROCESS = 2;//正在进行中
    public final static int STATUS_IS_OVERD = 3;//已结束

    //    类型
    public final static int TYPE_COIN_COMMON = 1;//普通送币活动
    public final static int TYPE_COIN_INVITATION = 2;//邀请注册
    public final static int TYPE_COIN_REGISTER = 3;//注册


    public final static int DSTATUS_TO_BE_EXTRACTED = 0;//待领取
    public final static int DSTATUS_ALREADY_RECEVIED = 1;//已领取
    public final static int DSTATUS_TEXPIRED = 2;//已过期
    public final static int DSTATUS_TO_BE_AUDITED = 3;//待审核
    public final static int DSTATUS_FAILED_AUDITED  = 4;//审批拒绝

    public final static String OPER_CODE_REGISTER  = "ZCHD";
    public final static String OPER_CODE_CHECKIN  = "QDHD";

	public static final BigDecimal REGISTER_AMOUNT = new BigDecimal(300);

	public static final BigDecimal CHECKIN_AMOUNT = BigDecimal.TEN;

}
