package com.mc.p2p.domain.video.repository;

import com.mc.p2p.domain.discern.entity.DiscernDo;
import com.mc.p2p.model.po.Video;

import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/24 10:40 PM
 */
public interface VideoRepository {

    /**
     * saveVideo
     *
     * @param record
     */
    void saveVideo(Video record);

    /**
     * updateVideo
     *
     * @param record
     */
    void updateVideo(Video record);

    /**
     * selectList
     *
     * @param openId
     * @return Video
     */
    List<Video> selectList(String openId);

    /**
     * likeCount
     *
     * @param openId
     */
    Integer likeCount(String openId);

    /**
     * selectOne
     *
     * @param videoId
     */
    Video selectOne(String videoId);

    /**
     * saveDiscern
     *
     * @param record
     * @param videoId
     */
    void saveDiscern(DiscernDo record, String videoId);
}
