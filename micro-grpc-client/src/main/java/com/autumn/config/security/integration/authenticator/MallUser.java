//package com.autumn.config.security.integration.authenticator;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.baomidou.mybatisplus.extension.activerecord.Model;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
///**
// * <p>
// *
// * </p>
// *
// * @author jlm
// * @since 2022-07-31
// */
//@Data
//@EqualsAndHashCode(callSuper = false)
//@TableName("mall_user")
//@ApiModel(value = "MallUser对象", description = "")
//public class MallUser extends Model<MallUser> {
//
//    private static final long serialVersionUID = 1L;
//
//    public MallUser() {
//    }
//
//    public MallUser(MallUser userinfo) {
//        this.startLevel = userinfo.getStartLevel();
//        this.startRelationship = userinfo.getStartRelationship();
//        this.id = userinfo.getId();
//        this.username = userinfo.getUsername();
//        this.password = userinfo.getPassword();
//        this.securityPwd = userinfo.getSecurityPwd();
//        this.gender = userinfo.getGender();
//        this.birthday = userinfo.getBirthday();
//    }
//
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;
//
//    @TableField("username")
//    private String username;
//
//    @TableField("password")
//    private String password;
//
//    @TableField("security_pwd")
//    private String securityPwd;
//
//    @ApiModelProperty(value = "性别：0 未知， 1男， 2 女")
//    @TableField("gender")
//    private Integer gender;
//
//    @TableField("birthday")
//    private LocalDateTime birthday;
//
//    @TableField("last_login_time")
//    private LocalDateTime lastLoginTime;
//
//    @TableField("last_login_ip")
//    private String lastLoginIp;
//
//    @TableField("nickname")
//    private String nickname;
//
//    @TableField("email")
//    private String email;
//
//    @TableField("mobile")
//    private String mobile;
//
//    @TableField("avatar")
//    private String avatar;
//
//    @ApiModelProperty(value = "常规验证方式 1-> 手机 2 ->邮箱")
//    @TableField("common_auth")
//    private Integer commonAuth;
//
//    @TableField("google_auth")
//    private String googleAuth;
//
//    @ApiModelProperty(value = "1 使用谷歌认证     0 未使用谷歌认证")
//    @TableField("use_google")
//    private Boolean useGoogle;
//
//    @ApiModelProperty(value = "0 普通用户")
//    @TableField("user_level")
//    private Integer userLevel;
//
//    @TableField("invitation_code")
//    private String invitationCode;
//
//    @ApiModelProperty(value = "被邀请人id")
//    @TableField("pid")
//    private Integer pid;
//
//    @ApiModelProperty(value = "0 未激活 1 激活")
//    @TableField("status")
//    private Integer status;
//
//    @TableField("update_time")
//    private LocalDateTime updateTime;
//
//    @TableField("add_time")
//    private LocalDateTime addTime;
//
//    @ApiModelProperty(value = "逻辑删除")
//    @TableField("deleted")
//    private Boolean deleted;
//
//    @ApiModelProperty(value = "层级等级")
//    @TableField("start_level")
//    private Integer startLevel;
//
//    @TableField("start_relationship")
//    private String startRelationship;
//
//    @ApiModelProperty(value = "版本")
//    @TableField("version")
//    private Long version;
//
//    @ApiModelProperty(value = "安全设置更改时间")
//    @TableField("security_updatetime")
//    private LocalDateTime securityUpdatetime;
//
//    @ApiModelProperty(value = "手机认证 1 开启 0 关闭")
//    @TableField("oauth_mobile")
//    private Boolean oauthMobile;
//
//    @ApiModelProperty(value = "认证邮箱 1 开启 0 关闭")
//    @TableField("oauth_email")
//    private Boolean oauthEmail;
//
//    @ApiModelProperty(value = "消费金额")
//    @TableField("consum_amount")
//    private BigDecimal consumAmount;
//
//
//    @Override
//    protected Serializable pkVal() {
//        return this.id;
//    }
//
//}
