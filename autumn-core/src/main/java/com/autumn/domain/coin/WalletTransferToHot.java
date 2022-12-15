package com.autumn.domain.coin;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jlm
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wallet_transfer_to_hot")
@ApiModel(value="WalletTransferToHot对象", description="")
public class WalletTransferToHot extends Model<WalletTransferToHot> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("coinid")
    private Integer coinid;

    @TableField("tx_hash")
    private String txHash;

    @TableField("num")
    private BigDecimal num;

    @TableField("add_time")
    private LocalDateTime addTime;

    @TableField("status")
    private Integer status;

    @TableField("from_address")
    private String fromAddress;

    @TableField("to_address")
    private String toAddress;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
