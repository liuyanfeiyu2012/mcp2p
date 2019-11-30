package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : Yuan.Pan 2019/11/30 11:34 AM
 */
@Data
public class CityCircleResp {

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("热度")
    private String hot;
}
