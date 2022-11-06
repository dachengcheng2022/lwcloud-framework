package com.autumn.wallet.domain.user;

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
 * 用户地址
 * </p>
 *
 * @author jlm
 * @since 2021-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_address")
@ApiModel(value="UserAddress对象", description="用户地址")
public class UserAddress extends Model<UserAddress> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "币种编号")
    @TableField("coinid")
    private Integer coinid;

    @TableField("address")
    private String address;

    @ApiModelProperty(value = "添加时间")
    @TableField("addtime")
    private LocalDateTime addtime;

    @ApiModelProperty(value = "1 启用	            0 禁用")
    @TableField("status")
    private Integer status;

    @TableField("publickey")
    private String publickey;

    @TableField("privkey")
    private String privkey;

    @TableField("user_id")
    private Integer userId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
