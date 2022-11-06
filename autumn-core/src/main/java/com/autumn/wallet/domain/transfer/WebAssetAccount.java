package com.autumn.wallet.domain.transfer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 用户钱包资产表
 * </p>
 *
 * @author jlm
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("web_asset_account")
@ApiModel(value="WebAssetAccount对象", description="用户钱包资产表")
public class WebAssetAccount extends Model<WebAssetAccount> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @TableField("coinid")
    private Integer coinid;

    @ApiModelProperty(value = "可用金额")
    @TableField("available")
    private BigDecimal available;

    @ApiModelProperty(value = "锁定金额(提币）")
    @TableField("locked")
    private BigDecimal locked;

    @ApiModelProperty(value = "最后修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
