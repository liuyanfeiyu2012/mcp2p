package com.mc.p2p.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : Yuan.Pan 2019/11/21 11:02 AM
 */
@ApiModel("评论查询返回RESP")
@Data
public class CommentResp {

    @ApiModelProperty("评论者ID")
    private String uid;

    @ApiModelProperty("评论者名称")
    private String userName;

    @ApiModelProperty("评论者头像uri'")
    private String avatarUri;

    @ApiModelProperty("评论内容")
    private String content;
}
