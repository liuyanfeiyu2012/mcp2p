package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Data
@ToString
@ApiModel("上传视频REQ")
public class VideoUploadReq {

    @NotBlank(message = "uid不能为空")
    @ApiModelProperty(value = "用户ID", required = true)
    private String uid;

    @ApiModelProperty(value = "视频背景音乐")
    private String bgmId;

    @NotNull(message = "音频时长不能为空")
    @ApiModelProperty(value = "视频时间长度", required = true)
    private Double videoTime;

    @ApiModelProperty(value = "视频描述")
    private String videoMemo;
}
