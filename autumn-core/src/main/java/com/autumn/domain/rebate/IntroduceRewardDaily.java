package com.autumn.domain.rebate;

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
 * 推荐奖励结算
 * </p>
 *
 * @author jlm
 * @since 2022-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("introduce_reward_daily")
@ApiModel(value="IntroduceRewardDaily对象", description="推荐奖励结算")
public class IntroduceRewardDaily extends Model<IntroduceRewardDaily> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("coinid")
    private Integer coinid;

    @ApiModelProperty(value = "金额")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "支付状态 0 未支付 1 已支付")
    @TableField("pay_status")
    private Integer payStatus;

    @ApiModelProperty(value = "类型 0 普通 1节点分红")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "其他")
    @TableField("exedata")
    private String exedata;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
