package com.autumn.wallet.domain.transfer;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 内部转账表
 * </p>
 *
 * @author jlm
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("web_asset_transfer")
@ApiModel(value="WebAssetTransfer对象", description="内部转账表")
public class WebAssetTransfer extends Model<WebAssetTransfer> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "uuid")
    @TableField("timestamp")
    private String timestamp;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "币种id")
    @TableField("coinid")
    private Integer coinid;

    @ApiModelProperty(value = "从什么钱包 1现货钱包 5提币钱包")
    @TableField("from_appl_id")
    private Integer fromApplId;

    @ApiModelProperty(value = "到什么钱包,1现货钱包 5提币钱包")
    @TableField("to_appl_id")
    private Integer toApplId;

    @ApiModelProperty(value = "划账金额")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "划账状态 0已提交 1成功 2失败 3补偿")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_date")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "需要进行补偿的UUID")
    @TableField("compensate_id")
    private String compensateId;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
