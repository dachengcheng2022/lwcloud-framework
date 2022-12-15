package com.autumn.form.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SendMessageParams {

    @ApiModelProperty(required = true, name = "emailOrMobile", value = "邮箱或者手机号")
    private String emailOrMobile;

    @ApiModelProperty(required = true, name = "message", value = "模板参数")
    private String[] templateParams;

    @ApiModelProperty(required = true, name = "message", value = "消息类型：CAPTCHA/DEFAULT_ELECTRICITY_CHARGES")
    private String templateType;

    private Integer validateType;

    private String language;
}
