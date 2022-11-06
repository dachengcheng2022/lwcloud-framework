package com.autumn.wallet.domain.system;

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
 * 移动版本
 * </p>
 *
 * @author jlm
 * @since 2022-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tmobileversion")
@ApiModel(value = "Tmobileversion对象", description = "移动版本")
public class Tmobileversion extends Model<Tmobileversion> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "mvid", type = IdType.AUTO)
    private Integer mvid;

    @TableField("mvname")
    private String mvname;

    @TableField("mversion")
    private String mversion;

    @TableField("mtime")
    private LocalDateTime mtime;

    @TableField("mcontent")
    private String mcontent;

    @TableField("mstatus")
    private Integer mstatus;

    @TableField("murl")
    private String murl;

    @TableField("misforceupdate")
    private Boolean misforceupdate;

    @ApiModelProperty(value = "1：android 2： IOS  3: h5")
    @TableField("apptype")
    private Integer apptype;


    @Override
    protected Serializable pkVal() {
        return this.mvid;
    }

}
