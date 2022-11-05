package com.bt.vo.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ContactUsForms {
    @ApiModelProperty(value = "应用名称")
    private String application;

    @ApiModelProperty(value = "应用链接")
    private String url;


    public ContactUsForms(String application, String url) {
        this.application = application;
        this.url = url;
    }
}
