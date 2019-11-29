package com.mc.p2p.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/26 11:08 AM
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

    public CustomerCentreResp(List<VideoQueryResp> videoList, List<VideoQueryResp> likeList, Integer likeCountTotal) {
        this.videoList = videoList;
        this.likeList = likeList;
        this.likeCountTotal = likeCountTotal;
    }
}
