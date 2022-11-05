package com.bt.wallet.form.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "微账户查询参数")
public class MircoUserAccountParams {
    @ApiModelProperty(required = false, name = "accountid", value = "账户ID，传则取单个账户的账户信息")
    private Integer accountid;
    @ApiModelProperty(required = false, name = "userid", value = "不用传，内部用的")
    private Integer userid;


    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "MircoUserAccountParams{" +
                "accountid=" + accountid +
                '}';
    }
}
