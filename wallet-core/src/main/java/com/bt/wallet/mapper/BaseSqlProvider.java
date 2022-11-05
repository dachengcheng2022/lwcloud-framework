package com.bt.wallet.mapper;

import java.util.List;

public interface BaseSqlProvider {
    default void assembleSqlWhere(StringBuilder sb, List<String> queryls) {
        for (int i = 0; i < queryls.size(); i++) {
            if (i == 0) {
                sb.append("");
            } else {
                sb.append(" and ");
            }
            sb.append(queryls.get(i));
        }
    }
}
