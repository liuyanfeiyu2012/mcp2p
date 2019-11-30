package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@ApiModel("背景音乐对象RESP")
@Data
public class BgmQueryResp {

    @ApiModelProperty("背景音乐ID")
    private String bgmId;

    @ApiModelProperty("背景音乐资源uri")
    private String bgmUri;

    @ApiModelProperty("背景音乐名称")
    private String bgmName;

    @ApiModelProperty("背景音乐背景图")
    private String bgmPic;
}
