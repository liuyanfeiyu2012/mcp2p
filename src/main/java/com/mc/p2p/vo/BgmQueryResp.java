package com.mc.p2p.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : Yuan.Pan 2019/11/21 10:53 AM
 */
@ApiModel("背景音乐对象RESP")
@Data
public class BgmQueryResp {

    @ApiModelProperty("背景音乐ID")
    private String bgmId;

    @ApiModelProperty("背景音乐资源uri")
    private String uri;
}
