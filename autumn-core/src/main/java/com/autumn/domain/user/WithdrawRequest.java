package com.autumn.domain.user;

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
 * 提币申请
 * </p>
 *
 * @author jlm
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("withdraw_request")
@ApiModel(value = "WithdrawRequest对象", description = "提币申请")
public class WithdrawRequest extends Model<WithdrawRequest> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "币种编号")
    @TableField("coinid")
    private Integer coinid;

    @ApiModelProperty(value = "用户编号")
    @TableField("userid")
    private Integer userid;

    @ApiModelProperty(value = "提币数量")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "平台手续费")
    @TableField("fees")
    private BigDecimal fees;

    @ApiModelProperty(value = "添加时间")
    @TableField("createtime")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "1 同意  2 不同意 3->审核中")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = " 备注")
    @TableField("request_remark")
    private String requestRemark;

    @ApiModelProperty(value = "审核备注")
    @TableField("detail")
    private String detail;

    @ApiModelProperty(value = "提币地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "提币记录编号")
    @TableField("txoutid")
    private Integer txoutid;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
