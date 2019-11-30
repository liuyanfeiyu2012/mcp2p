package com.mc.p2p.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankResp rankResp = (RankResp) o;
        return Objects.equals(videoId, rankResp.videoId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(videoId);
    }
}
