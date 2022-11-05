package com.bt.common;

public enum AccountStreamType {
	
	DEPOSIT(1,"充值"),
	TRANSFER(2,"转币"),
	RECEIVE(3,"收币"),
	SEND_REDPACK(4,"发红包"),
	RECV_REDPACK(5,"收红包"),
	DRAW(6,"提币"),
	OTC_BUY(7,"OTC买币"),
	OTC_SELL(8,"OTC卖币"),
	BBEX_BUY(9,"币币交易买币"),
	BBEX_SELL(10,"币币交易卖币"),
	ACT_SEND(11,"活动送币");

    private Integer streamType;
    private String value;

    AccountStreamType(Integer streamType, String value) {
        this.streamType = streamType;
        this.value = value;
    }

	public Integer getStreamType() {
		return streamType;
	}

	public String getValue() {
		return value;
	}

}
