package com.autumn.wallet.form.account;/**
 * Created by Administrator on 2018/12/4.
 */


import java.math.BigDecimal;

/**
 * @program: xwallet
 * @description:PublishCoinRecord
 * @author: Woody
 * @createtime: 2018-12-04 15:43
 **/
public class PublishCoinRecordForm {
    private String coinKey ;
    private BigDecimal totalmoney;
    private Integer amount;

    public String getCoinKey() {
        return coinKey;
    }

    public void setCoinKey(String coinKey) {
        this.coinKey = coinKey;
    }

    public BigDecimal getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(BigDecimal totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
