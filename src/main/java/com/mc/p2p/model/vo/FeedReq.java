package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@ApiModel("投食对象REQ")
@Data
public class FeedReq {

    private String videoId;

    private String ownerId;
}
