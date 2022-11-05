package com.bt.vo.coin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CoinQuotationVo {
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "漲跌幅")
    private BigDecimal range;

    @ApiModelProperty(value = "币种符号")
    private String symbol;

}
