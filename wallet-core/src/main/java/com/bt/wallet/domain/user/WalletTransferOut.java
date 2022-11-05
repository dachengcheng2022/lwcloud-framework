//package com.bt.wallet.domain.user;
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
//import lombok.experimental.Accessors;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
///**
// * <p>
// * 转出表
// * </p>
// *
// * @author jlm
// * @since 2021-11-16
// */
//@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
//@TableName("wallet_transfer_out")
//@ApiModel(value="WalletTransferOut对象", description="转出表")
//public class WalletTransferOut extends Model<WalletTransferOut> {
//
//    private static final long serialVersionUID = 1L;
//
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;
//
//    @ApiModelProperty(value = "币种编号")
//    @TableField("coinid")
//    private Integer coinid;
//
//    @TableField("fromaddress")
//    private String fromaddress;
//
//    @TableField("toaddress")
//    private String toaddress;
//
//    @ApiModelProperty(value = "上链高度")
//    @TableField("height")
//    private Integer height;
//
//    @TableField("addtime")
//    private LocalDateTime addtime;
//
//    @ApiModelProperty(value = "确认高度")
//    @TableField("comfirmnum")
//    private Integer comfirmnum;
//
//    @ApiModelProperty(value = "0 未上链，进行中  2 上链失败  1 上链成功")
//    @TableField("status")
//    private Integer status;
//
//    @ApiModelProperty(value = "平台手续费")
//    @TableField("fees")
//    private BigDecimal fees;
//
//    @ApiModelProperty(value = "转入数量")
//    @TableField("num")
//    private BigDecimal num;
//
//    @TableField("remark")
//    private String remark;
//
//    @TableField("endtime")
//    private LocalDateTime endtime;
//
//    @TableField("user_id")
//    private Integer userId;
//
//    @TableField("block_tx_link")
//    private String blockTxLink;
//
//    @TableField("txid")
//    private String txid;
//
//    @ApiModelProperty(value = "1 外部转账  2 矿机收益转出")
//    @TableField("txtype")
//    private Integer txtype;
//
//
//    @Override
//    protected Serializable pkVal() {
//        return this.id;
//    }
//
//}
