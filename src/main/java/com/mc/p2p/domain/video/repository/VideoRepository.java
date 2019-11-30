package com.mc.p2p.domain.video.repository;

import com.mc.p2p.domain.discern.entity.DiscernDo;
import com.mc.p2p.model.po.Video;

import java.util.List;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
public interface VideoRepository {

    /**
     * saveVideo
     *
     * @param record 视频
     */
    void saveVideo(Video record);

    /**
     * updateVideo
     *
     * @param record 视频
     */
    void updateVideo(Video record);

    /**
     * selectList
     *
     * @param openId 用户ID
     * @return Video
     */
    List<Video> selectList(String openId);

    /**
     * likeCount
     *
     * @param openId 用户ID
     * @return 投食数量
     */
    Integer likeCount(String openId);

    /**
     * selectOne
     *
     * @param videoId 视频编号
     * @return 视频
     */
    Video selectOne(String videoId);

    /**
     * saveDiscern
     *
     * @param record 分析结果
     * @param videoId 视频编号
     */
    void saveDiscern(DiscernDo record, String videoId);
}
