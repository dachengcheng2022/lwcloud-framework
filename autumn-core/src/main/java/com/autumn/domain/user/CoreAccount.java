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
 * 账户资产表
 * </p>
 *
 * @author jlm
 * @since 2021-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("core_account")
@ApiModel(value="CoreAccount对象", description="账户资产表")
public class CoreAccount extends Model<CoreAccount> {


    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "应用标识 1-> 币币余额账户 2 ->矿机收益账户 3->外部应用")
    @TableField("appl_id")
    private Integer applId;

    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "币种ID")
    @TableField("coinid")
    private Integer coinid;

    @ApiModelProperty(value = "总资产")
    @TableField("total_money")
    private BigDecimal totalMoney;

    @ApiModelProperty(value = "委托冻结金额")
    @TableField("order_frozen_money")
    private BigDecimal orderFrozenMoney;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Long updateTime;


    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    @TableField("version")
    private Long version;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
