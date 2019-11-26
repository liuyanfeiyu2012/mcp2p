package com.mc.p2p.model.vo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/26 11:08 AM
 */
@Data
public class CustomerCentreResp {

    /** 个人视频列表 */
    private List<VideoQueryResp> videoList = Lists.newArrayList();

    /** 喜欢列表 */
    private List<VideoQueryResp> likeList = Lists.newArrayList();

    /** 总计喜欢数 */
    private Integer likeCountTotal;
}
