package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * @author : Yuan.Pan 2019/11/21 10:58 AM
 */
@ApiModel("视频信息RESP")
@Data
public class VideoQueryResp {

    @ApiModelProperty("视频ID")
    private String videoId;

    @ApiModelProperty("视频地址")
    private String videoUri;

    @ApiModelProperty("视频背景图")
    private String videoBgUri;

    @ApiModelProperty("视频说明")
    private String videoMemo;

    @ApiModelProperty("喜欢数")
    private Integer likeCount;

    @ApiModelProperty("微信名称")
    private String wxName;

    @ApiModelProperty("微信头像")
    private String avatar;

    @ApiModelProperty("动物名称")
    private String animal;

    @ApiModelProperty("动物科普知识")
    private String description;

    @ApiModelProperty("智能推荐商品")
    private Set<String> recommendProduct;
}
