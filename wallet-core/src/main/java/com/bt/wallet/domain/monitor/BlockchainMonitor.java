package com.bt.wallet.domain.monitor;

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
 * 链上监控
 * </p>
 *
 * @author jlm
 * @since 2022-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("blockchain_monitor")
@ApiModel(value="BlockchainMonitor对象", description="链上监控")
public class BlockchainMonitor extends Model<BlockchainMonitor> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "币种编号")
    @TableField("coin_id")
    private Integer coinId;

    @TableField("address")
    private String address;

    @ApiModelProperty(value = "金额")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
