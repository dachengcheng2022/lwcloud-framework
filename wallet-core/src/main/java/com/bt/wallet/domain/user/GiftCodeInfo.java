package com.bt.wallet.domain.user;

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
 * 礼品码信息
 * </p>
 *
 * @author jlm
 * @since 2022-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gift_code_info")
@ApiModel(value="GiftCodeInfo对象", description="礼品码信息")
public class GiftCodeInfo extends Model<GiftCodeInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("gift_code")
    private String giftCode;

    @TableField("coin_id")
    private Integer coinId;

    @TableField("coin_symbol")
    private String coinSymbol;

    @ApiModelProperty(value = "0 未兑换 1 已经兑换")
    @TableField("status")
    private Integer status;

    @TableField("amount")
    private BigDecimal amount;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("end_time")
    private LocalDateTime endTime;

    @TableField("user_id")
    private Integer userId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
