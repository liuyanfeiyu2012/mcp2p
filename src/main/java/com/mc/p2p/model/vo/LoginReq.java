package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author : Yuan.Pan 2019/11/26 10:54 AM
 */
@Data
@ApiModel("登录REQ")
public class LoginReq {

    @NotBlank(message = "微信昵称不能为空")
    @ApiModelProperty(value = "微信昵称", required = true)
    private String wxName;

    @NotBlank(message = "openId 不能为空")
    @ApiModelProperty(value = "微信账户唯一标识", required = true)
    private String openId;

    @NotBlank(message = "微信头像不能为空")
    @ApiModelProperty(value = "微信头像", required = true)
    private String avatar;
}