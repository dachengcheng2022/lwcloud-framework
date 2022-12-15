package com.autumn.domain.message;

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
 * @since 2022-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tverifyinfo")
@ApiModel(value="Tverifyinfo对象", description="")
public class Tverifyinfo extends Model<Tverifyinfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    @ApiModelProperty(value = "验证类型")
    @TableField("ttype")
    private Integer ttype;

    @TableField("tcode")
    private String tcode;

    @TableField("tstarttime")
    private LocalDateTime tstarttime;

    @TableField("tendtime")
    private LocalDateTime tendtime;

    @TableField("taccount")
    private String taccount;

    @ApiModelProperty(value = "发送状态")
    @TableField("tsendflag")
    private Integer tsendflag;

    @ApiModelProperty(value = "失效时间分钟")
    @TableField("tinvalidminute")
    private Integer tinvalidminute;

    @ApiModelProperty(value = "是否有效")
    @TableField("tisvalid")
    private Boolean tisvalid;


    @Override
    protected Serializable pkVal() {
        return this.tid;
    }

}
