package com.mc.p2p.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Data
public class CustomerCentreResp {

    /**
     * 个人视频列表
     */
    private List<VideoQueryResp> videoList;

    /**
     * 喜欢列表
     */
    private List<VideoQueryResp> likeList;

    /**
     * 总计喜欢数
     */
    private Integer likeCountTotal;

    public CustomerCentreResp(List<VideoQueryResp> videoList,
                              List<VideoQueryResp> likeList,
                              Integer likeCountTotal) {
        this.videoList = videoList;
        this.likeList = likeList;
        this.likeCountTotal = likeCountTotal;
    }
}
