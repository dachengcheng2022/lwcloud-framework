package com.autumn.common;

import java.util.HashMap;
import java.util.Map;

public enum ChainType {
    main(1), fork(2), token(3);


    public final Integer CHAIN_CODE;
    private static Map<Integer, ChainType> codeLookup = new HashMap<>();

    static {
        for (ChainType type : ChainType.values()) {
            codeLookup.put(type.CHAIN_CODE, type);
        }
    }

    ChainType(Integer code) {
        this.CHAIN_CODE = code;
    }
}
