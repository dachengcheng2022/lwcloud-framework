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
 * 理财产品
 * </p>
 *
 * @author jlm
 * @since 2022-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("financial_product")
@ApiModel(value="FinancialProduct对象", description="理财产品")
public class FinancialProduct extends Model<FinancialProduct> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "产品名称")
    @TableField("project_name")
    private String projectName;

    @ApiModelProperty(value = "排序")
    @TableField("project_order")
    private Integer projectOrder;

    @ApiModelProperty(value = "true 上架 false 下架")
    @TableField("project_status")
    private Boolean projectStatus;

    @ApiModelProperty(value = "产品介绍")
    @TableField("project_introduce")
    private String projectIntroduce;

    @ApiModelProperty(value = "收益率")
    @TableField("project_yield")
    private BigDecimal projectYield;

    @ApiModelProperty(value = "理财周期")
    @TableField("project_cycle")
    private Integer projectCycle;

    @ApiModelProperty(value = "币种编号")
    @TableField("coin_id")
    private Integer coinId;

    @ApiModelProperty(value = "币种符号")
    @TableField("symbol")
    private String symbol;

    @ApiModelProperty(value = "限购次数")
    @TableField("buy_limit")
    private Integer buyLimit;

    @TableField("lowest_amount")
    private BigDecimal lowestAmount;

    @ApiModelProperty(value = "最高投入数量")
    @TableField("hightest_amount")
    private BigDecimal hightestAmount;

    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "产品图片")
    @TableField("product_img")
    private String productImg;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
