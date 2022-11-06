package com.autumn.wallet.domain.stat;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 每日流水统计
 * </p>
 *
 * @author jlm
 * @since 2022-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("admindaily_income_stat")
@ApiModel(value="AdmindailyIncomeStat对象", description="每日流水统计")
public class AdmindailyIncomeStat extends Model<AdmindailyIncomeStat> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "充值金额")
    @TableField("depositamount")
    private BigDecimal depositamount;

    @ApiModelProperty(value = "提币金额")
    @TableField("withdrawamount")
    private BigDecimal withdrawamount;

    @ApiModelProperty(value = "挖矿收益")
    @TableField("mineramount")
    private BigDecimal mineramount;

    @ApiModelProperty(value = "返佣收益")
    @TableField("serviceamount")
    private BigDecimal serviceamount;

    @ApiModelProperty(value = "更新时间")
    @TableField("updateat")
    private LocalDateTime updateat;

    @ApiModelProperty(value = "币种编号")
    @TableField("coinid")
    private Integer coinid;

    @ApiModelProperty(value = "平台总资金")
    @TableField("total_money")
    private BigDecimal totalMoney;

    @ApiModelProperty(value = "商城消费")
    @TableField("mall_consume")
    private BigDecimal mallConsume;

    @ApiModelProperty(value = "矿机挖矿")
    @TableField("miner_income")
    private BigDecimal minerIncome;

    @ApiModelProperty(value = "算力挖矿")
    @TableField("hash_income")
    private BigDecimal hashIncome;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
