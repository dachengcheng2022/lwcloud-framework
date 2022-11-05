package com.bt.wallet.form;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageForm {
    @ApiModelProperty(value = "当前页", required = true)
    @NotNull
    private Long current = 1L;

    @ApiModelProperty(name = "每页显示条数", required = true)
    @NotNull
    private Long size = 50L;

}
