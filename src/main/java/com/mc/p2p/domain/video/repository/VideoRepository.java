package com.mc.p2p.domain.video.repository;

import com.mc.p2p.model.po.Video;

import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/24 10:40 PM
 */
public interface VideoRepository {

    void saveVideo(Video record);

    List<Video> selectList();
}
