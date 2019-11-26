package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Auther: 谢星星
 * @Date: 2019/11/26 20:41
 * @Description:
 */
@ApiModel("投食对象REQ")
@Data
public class FeedReq {

    private String videoId;

    private String ownerId;
}
