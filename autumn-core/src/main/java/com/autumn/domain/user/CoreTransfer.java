package com.autumn.domain.user;

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
 * 内部转账表，矿机收益结算，
 * </p>
 *
 * @author jlm
 * @since 2021-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("core_transfer")
@ApiModel(value="CoreTransfer对象", description="内部转账表，矿机收益结算，")
public class CoreTransfer extends Model<CoreTransfer> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("transfer_id")
    private String transferId;

    @ApiModelProperty(value = " 源应用标识 1 币币账户 5 钱包账户  20  挖矿收益 30 商城应用")
    @TableField("from_appl_id")
    private Integer fromApplId;

    @ApiModelProperty(value = "目标应用标识")
    @TableField("to_appl_id")
    private Integer toApplId;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @TableField("coinid")
    private Integer coinid;

    @ApiModelProperty(value = "数量")
    @TableField("quantity")
    private BigDecimal quantity;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新前价格")
    @TableField("prev_quantity")
    private BigDecimal prevQuantity;

    @ApiModelProperty(value = "更新后价格")
    @TableField("current_quantity")
    private BigDecimal currentQuantity;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
