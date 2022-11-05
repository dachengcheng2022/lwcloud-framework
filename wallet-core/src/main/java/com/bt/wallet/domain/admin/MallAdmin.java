package com.bt.wallet.domain.admin;

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
 * @since 2022-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mall_admin")
@ApiModel(value = "MallAdmin对象", description = "")
public class MallAdmin extends Model<MallAdmin> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("last_login_ip")
    private String lastLoginIp;

    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @TableField("avatar")
    private String avatar;

    @TableField("add_time")
    private LocalDateTime addTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("deleted")
    private Boolean deleted;

    @TableField("role_ids")
    private String roleIds;

    @TableField("google_auth")
    private String googleAuth;

    public MallAdmin(MallAdmin mallAdmin) {
        this.id = mallAdmin.getId();
        this.username = mallAdmin.getUsername();
        this.password = mallAdmin.getPassword();
    }


    public MallAdmin() {
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
