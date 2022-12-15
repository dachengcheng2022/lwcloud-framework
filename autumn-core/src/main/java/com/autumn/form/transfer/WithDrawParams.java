package com.autumn.form.transfer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("提币参数")
@Data
public class WithDrawParams {
//    @JsonIgnore
    private Integer userid;

    private Integer coinid;

    private BigDecimal amount;

    private String remark;

    private String toAddress;

    private Boolean autoWithdraw;

    @ApiModelProperty(required = true, name = "assetpassword", value = "短信验证码")
    private String assetpassword;

}
