package com.autumn.wallet.domain.rebate;

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
 * 推荐奖励
 * </p>
 *
 * @author jlm
 * @since 2022-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("introduce_reward")
@ApiModel(value="IntroduceReward对象", description="推荐奖励")
public class IntroduceReward extends Model<IntroduceReward> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "来源ID")
    @TableField("resource_id")
    private Integer resourceId;

    @ApiModelProperty(value = "支付金额，单位USD")
    @TableField("amount")
    private BigDecimal amount;

    @TableField("order_no")
    private String orderNo;

    @ApiModelProperty(value = "类型 0 普通 1节点分红")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "层级")
    @TableField("hierarchical")
    private Integer hierarchical;

    @ApiModelProperty(value = "节点等级 0->普通用户 1-> 体验矿工 2-> 小矿工 3->中矿工 4->大矿工 5->超级矿工")
    @TableField("node_level")
    private Integer nodeLevel;

    @ApiModelProperty(value = "支付状态 0 未支付 1 已支付")
    @TableField("pay_status")
    private Integer payStatus;

    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value ="币种编号")
    @TableField("coin_id")
    private Integer coinId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
