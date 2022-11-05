package com.bt.wallet.domain.coin;

import java.math.BigDecimal;
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
 * 币信息
 * </p>
 *
 * @author jlm
 * @since 2022-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tcoininfo")
@ApiModel(value="Tcoininfo对象", description="币信息")
public class Tcoininfo extends Model<Tcoininfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "coinid", type = IdType.AUTO)
    private Integer coinid;

    @TableField("cnamecn")
    private String cnamecn;

    @TableField("cnameen")
    private String cnameen;

    @TableField("csymbol")
    private String csymbol;

    @ApiModelProperty(value = "0未启用，1启用")
    @TableField("cstatus")
    private Integer cstatus;

    @TableField("accesskey")
    private String accesskey;

    @TableField("secretkey")
    private String secretkey;

    @TableField("cip")
    private String cip;

    @ApiModelProperty(value = "钱包")
    @TableField("cport")
    private Integer cport;

    @ApiModelProperty(value = "交易确认数")
    @TableField("confirmationnum")
    private Integer confirmationnum;

    @TableField("cbrowseurl")
    private String cbrowseurl;

    @ApiModelProperty(value = "1：UTXO  2：account类型    3、智能坊系列     4、bts系列")
    @TableField("cointype")
    private Integer cointype;

    @TableField("ciconpath")
    private String ciconpath;

    @ApiModelProperty(value = "提币手续费")
    @TableField("withdrawfee")
    private BigDecimal withdrawfee;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "最小支付金额")
    @TableField("min_payment")
    private BigDecimal minPayment;

    @ApiModelProperty(value = "是否允许提币")
    @TableField("allowwithdraw")
    private Boolean allowwithdraw;

    @ApiModelProperty(value = "充值")
    @TableField("allowdeposite")
    private Boolean allowdeposite;

    @ApiModelProperty(value = "官网")
    @TableField("official_website")
    private String officialWebsite;

    @ApiModelProperty(value = "是否在首页展示")
    @TableField("index_show")
    private Boolean indexShow;

    @TableField("max_payment")
    private BigDecimal maxPayment;

    @ApiModelProperty(value = "地址")
    @TableField("address_regular")
    private String addressRegular;


    @Override
    protected Serializable pkVal() {
        return this.coinid;
    }

}
