package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : Yuan.Pan 2019/11/26 8:31 PM
 */
@Data
@ApiModel("评论REQ")
public class CommentReq {

    @NotBlank(message = "品论视频不能为空")
    @ApiModelProperty("被品论视频ID")
    private String videoId;

    @NotBlank(message = "请登录~")
    @ApiModelProperty("用户ID")
    private String uid;
}
