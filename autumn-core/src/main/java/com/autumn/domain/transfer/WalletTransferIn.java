package com.autumn.domain.transfer;

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
 * @since 2021-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wallet_transfer_in")
@ApiModel(value="WalletTransferIn对象", description="")
public class WalletTransferIn extends Model<WalletTransferIn> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "币种编号")
    @TableField("coinid")
    private Integer coinid;

    @TableField("user_id")
    private Integer userId;

    @TableField("fromaddress")
    private String fromaddress;

    @TableField("toaddress")
    private String toaddress;

    @ApiModelProperty(value = "上链高度")
    @TableField("height")
    private Integer height;

    @TableField("addtime")
    private LocalDateTime addtime;

    @ApiModelProperty(value = "确认高度")
    @TableField("comfirmnum")
    private Integer comfirmnum;

    @ApiModelProperty(value = "0 未划转      1 已划转")
    @TableField("sendstatus")
    private Integer sendstatus;

    @ApiModelProperty(value = "平台手续费")
    @TableField("fees")
    private BigDecimal fees;

    @ApiModelProperty(value = "转入数量")
    @TableField("num")
    private BigDecimal num;

    @ApiModelProperty(value = "0 待上链       2 失败 1 成功")
    @TableField("status")
    private Integer status;

    @TableField("endtime")
    private LocalDateTime endtime;

    @TableField("remark")
    private String remark;

    @TableField("block_tx_link")
    private String blockTxLink;

    @TableField("block_confirm_num")
    private Integer blockConfirmNum;

    @TableField("txid")
    private String txid;

    @ApiModelProperty(value = "1 外部转账  2 内部转账")
    @TableField("txtype")
    private Integer txtype;

    @TableField("collect_status")
    private Boolean collectStatus;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
