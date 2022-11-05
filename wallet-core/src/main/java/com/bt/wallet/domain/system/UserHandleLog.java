package com.bt.wallet.domain.system;

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
 * 用户操作日志
 * </p>
 *
 * @author jlm
 * @since 2022-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_handle_log")
@ApiModel(value="UserHandleLog对象", description="用户操作日志")
public class UserHandleLog extends Model<UserHandleLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "操作时间")
    @TableField("handle_time")
    private LocalDateTime handleTime;

    @ApiModelProperty(value = "操作类型 1 成功 0 失败")
    @TableField("handle_type")
    private Integer handleType;

    @ApiModelProperty(value = "操作ip")
    @TableField("handle_ip")
    private String handleIp;

    @ApiModelProperty(value = "操作方法")
    @TableField("handle_method")
    private String handleMethod;

    @ApiModelProperty(value = "用户编号")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "参数")
    @TableField("handle_params")
    private String handleParams;

    @ApiModelProperty(value = "描述")
    @TableField("handle_desc")
    private String handleDesc;

    @ApiModelProperty(value = "请求信息")
    @TableField("message")
    private String message;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
