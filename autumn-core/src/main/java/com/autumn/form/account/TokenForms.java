package com.autumn.form.account;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TokenForms {
    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "token_type")
    private String tokenType;
    @JSONField(name = "refresh_token")
    private String refreshToken;
    @JSONField(name = "expiresIn")
    private Long expiresIn;
    @JSONField(name = "scope")
    private String scope;
    private Integer userId;

}
