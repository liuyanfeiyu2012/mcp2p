package com.mc.p2p.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : Yuan.Pan 2019/11/21 10:15 AM
 */
@Data
@ApiModel("上传视频REQ")
public class UploadReq {

    @ApiModelProperty(value = "用户ID", required = true)
    private String uid;

    @ApiModelProperty(value = "视频背景音乐")
    private String bgmId;

    @ApiModelProperty(value = "视频时间长度", required = true)
    private double videoTime;
}
