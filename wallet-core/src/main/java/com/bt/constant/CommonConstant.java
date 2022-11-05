package com.bt.constant;

import com.bt.vo.common.ContactUsForms;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CommonConstant {

    public final static Integer REDIS_EXPIRE_MIN = 10000;

    //    USDT默认汇率
    public final static String DEFAULT_USDTRATE = "6.31";

    //    订单返佣比例 10%
    public final static BigDecimal ORDER_REBATE_RATE = new BigDecimal("0.1");

	public static final String PRODUCT_NAME = "TokenInsure";

    public static List<ContactUsForms> CONTACT_US_CONSTANT = Arrays.asList(
            new ContactUsForms("email", "xxxx@gmail.com"),
//            new ContactUsForms("vx", "123456"),
            new ContactUsForms("Twitter", "xxx(tokeninsure@gmail.com)"));
//            new ContactUsForms("Youtube", "123456"),
//            new ContactUsForms("Reddit", "123456"),
//            new ContactUsForms("Telegram", "123456"));

    public final static Integer ORDER_RUNNING = 0;

    public final static Integer ORDER_FINISHED = 1;

    public final static Integer ORDER_SYSTEM_COMBACK = 2;

}
