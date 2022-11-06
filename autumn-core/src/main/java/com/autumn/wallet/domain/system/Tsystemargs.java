package com.autumn.wallet.domain.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tsystemargs")
@ApiModel(value="Tsystemargs对象", description="")
public class Tsystemargs extends Model<Tsystemargs> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tsaid", type = IdType.AUTO)
    private Integer tsaid;

    @TableField("tsakey")
    private String tsakey;

    @TableField("tsavalue")
    private String tsavalue;

    @TableField("tsadesc")
    private String tsadesc;

    @TableField("tsaurl")
    private String tsaurl;

    @TableField("support_modify")
    private Boolean supportModify;


    @Override
    protected Serializable pkVal() {
        return this.tsaid;
    }

}
