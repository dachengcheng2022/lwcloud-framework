package com.autumn.common;

import java.util.HashMap;
import java.util.Map;

public enum CoinSeriesType {
    btcseries(1, "BTC"), ethseries(2, "ETH"), dacrsseries(3, "DACRS"), btsseries(4, "BTS"), moacseries(1005, "MOAC"), tokenseries(9999, "TOKEN");
    public final int COIN_TYPE;
    public final String logName;

    CoinSeriesType(int coinType, String logName) {
        this.COIN_TYPE = coinType;
        this.logName = logName;
    }

    private static Map<Integer, CoinSeriesType> codeLookup = new HashMap<>();

    static {
        for (CoinSeriesType type : CoinSeriesType.values()) {
            codeLookup.put(type.COIN_TYPE, type);
        }
    }

    public String getLogName() {
		return logName;
	}

	public static CoinSeriesType forCode(int coinType) {
        return codeLookup.get(coinType);
    }

    public static Integer exValue(String v) {
        Integer result = null;
        switch (v) {
            case "btcseries":
                result = 1;
                break;
            case "ethseries":
                result = 2;
                break;
            case "dacrsseries":
                result = 3;
                break;
            case "btsseries":
                result = 4;
                break;
            case "moacseries":
                result = 5;
                break;
            default:
                break;
        }

        return result;
    }
}
