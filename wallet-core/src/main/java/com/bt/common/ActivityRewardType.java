package com.bt.common;

public enum ActivityRewardType {
	
	REGISTER(1,"注册送币"),
	CHECKIN(2,"签到送币");

    private Integer rewardType;
    private String value;

    ActivityRewardType(Integer rewardType, String value) {
        this.rewardType = rewardType;
        this.value = value;
    }

	public Integer getRewardType() {
		return rewardType;
	}

	public String getValue() {
		return value;
	}

}
