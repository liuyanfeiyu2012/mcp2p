package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 谢星星
 * @Date: 2019/11/26 20:57
 * @Description:
 */
@ApiModel("排行榜REQ")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankResp {

    private String videoId;

    private String ownerId;

    private Integer score;

    private String avatar;

    private String wxName;

    private String videoUrl;

    private String videoBgUrl;
}
