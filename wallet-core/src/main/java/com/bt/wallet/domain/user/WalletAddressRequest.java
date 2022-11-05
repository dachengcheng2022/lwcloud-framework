package com.bt.wallet.domain.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 地址请求信息
 * </p>
 *
 * @author jlm
 * @since 2021-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wallet_address_request")
@ApiModel(value="WalletAddressRequest对象", description="地址请求信息")
public class WalletAddressRequest extends Model<WalletAddressRequest> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("coinid")
    private Integer coinid;

    @TableField("add_time")
    private LocalDateTime addTime;

    @TableField("request_url")
    private String requestUrl;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
