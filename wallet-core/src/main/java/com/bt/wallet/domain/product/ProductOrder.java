package com.bt.wallet.domain.product;

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
 * 产品订单
 * </p>
 *
 * @author jlm
 * @since 2022-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_order")
@ApiModel(value="ProductOrder对象", description="产品订单")
public class ProductOrder extends Model<ProductOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("product_id")
    private Integer productId;

    @ApiModelProperty(value = "订单号")
    @TableField("order_num")
    private String orderNum;

    @TableField("product_name")
    private String productName;

    @TableField("coin_id")
    private Integer coinId;

    @TableField("symbol")
    private String symbol;

    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "0 奖励中 1中途赎回 2 已经完成")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty(value = "奖励次数")
    @TableField("income_times")
    private Integer incomeTimes;

    @ApiModelProperty(value = "奖励总次数")
    @TableField("expect_times")
    private Integer expectTimes;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "用户编号")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "下次收益时间")
    @TableField("netx_reward_time")
    private LocalDateTime netxRewardTime;

    @ApiModelProperty(value = "奖励时间")
    @TableField("reward_time")
    private LocalDateTime rewardTime;

    @TableField("project_yield")
    private BigDecimal projectYield;

    @ApiModelProperty(value = "返佣状态")
    @TableField("rebate_status")
    private Boolean rebateStatus;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
