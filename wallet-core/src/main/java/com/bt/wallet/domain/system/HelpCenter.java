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
 * 帮助中心
 * </p>
 *
 * @author jlm
 * @since 2022-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("help_center")
@ApiModel(value="HelpCenter对象", description="帮助中心")
public class HelpCenter extends Model<HelpCenter> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "内容")
    @TableField("tcontent")
    private String tcontent;

    @TableField("language")
    private String language;

    @ApiModelProperty(value = "排序")
    @TableField("torder")
    private Integer torder;

    @ApiModelProperty(value = "链接")
    @TableField("url_link")
    private String urlLink;

    @ApiModelProperty(value = "更新时间")
    @TableField("updatetime")
    private LocalDateTime updatetime;

    @TableField("createtime")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "问题类型  1 常见问题")
    @TableField("issue_type")
    private String issueType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
