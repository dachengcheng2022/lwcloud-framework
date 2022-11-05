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
 * 
 * </p>
 *
 * @author jlm
 * @since 2022-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_income_detail")
@ApiModel(value="ProductIncomeDetail对象", description="")
public class ProductIncomeDetail extends Model<ProductIncomeDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("order_id")
    private Integer orderId;

    @TableField("product_id")
    private Integer productId;

    @TableField("income_amount")
    private BigDecimal incomeAmount;

    @TableField("coin_id")
    private Integer coinId;

    @TableField("coin_symbol")
    private String coinSymbol;

    @TableField("user_id")
    private Integer userId;

    @TableField("reward_time")
    private Integer rewardTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "0 未完成 1 完成")
    @TableField("status")
    private Integer status;

    @TableField("base_amount")
    private BigDecimal baseAmount;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
