package com.autumn.domain.system;

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
 * 公告
 * </p>
 *
 * @author jlm
 * @since 2022-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_announcement")
@ApiModel(value="SystemAnnouncement对象", description="公告")
public class SystemAnnouncement extends Model<SystemAnnouncement> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "主题")
    @TableField("title")
    private String title;

    @TableField("link")
    private String link;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @TableField("black_groupd_url")
    private String blackGroupdUrl;

    @ApiModelProperty(value = "zh中文 en 英文 ko韩语 ja日语")
    @TableField("language")
    private String language;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "作者")
    @TableField("auther")
    private String auther;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
