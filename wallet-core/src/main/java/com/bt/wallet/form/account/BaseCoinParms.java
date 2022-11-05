package com.bt.wallet.form.account;

import io.swagger.annotations.ApiModelProperty;


public class BaseCoinParms {
    @ApiModelProperty(required = false, name = "coinid", value = "币种ID")
    private Integer coinid;
    @ApiModelProperty(required = false, name = "tokenid", value = "token ID")
    private Integer tokenid;
    @ApiModelProperty(required = true, name = "istoken", value = "是否代币")
    private Boolean istoken;

    public Integer getCoinid() {
        if (istoken != null && istoken) this.coinid = null;
        return coinid;
    }

    public void setCoinid(Integer coinid) {
        this.coinid = coinid;
    }

    public Integer getTokenid() {
        if (istoken != null && !istoken) this.tokenid = null;
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public Boolean getIstoken() {
        return istoken;
    }

    public void setIstoken(Boolean istoken) {
        this.istoken = istoken;
    }

    @Override
    public String toString() {
        return "BaseCoinParms{" +
                "coinid=" + coinid +
                ", tokenid=" + tokenid +
                ", istoken=" + istoken +
                '}';
    }
}
