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
 * 理财每日收益
 * </p>
 *
 * @author jlm
 * @since 2022-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_income_daily")
@ApiModel(value="ProductIncomeDaily对象", description="理财每日收益")
public class ProductIncomeDaily extends Model<ProductIncomeDaily> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户编号")
    @TableField("user_id")
    private Integer userId;

    @TableField("total_amount")
    private BigDecimal totalAmount;

    @TableField("coin_id")
    private Integer coinId;

    @TableField("symbol")
    private String symbol;

    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "0 未完成 1 完成")
    @TableField("status")
    private Integer status;

    @TableField("detail_id")
    private String detailId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
