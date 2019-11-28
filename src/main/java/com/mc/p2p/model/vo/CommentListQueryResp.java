package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel("评论列表返回值")
@Data
@Builder
public class CommentListQueryResp {

    @ApiModelProperty("评论情感")
    private String sentiment;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("评论文本")
    private String context;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户Id")
    private String userId;

    @ApiModelProperty("评论得分")
    private String score;
}
