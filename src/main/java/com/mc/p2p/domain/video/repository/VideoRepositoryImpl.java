package com.mc.p2p.domain.video.repository;

import com.mc.p2p.mapper.VideoMapper;
import com.mc.p2p.model.po.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : Yuan.Pan 2019/11/24 10:41 PM
 */
@Repository
public class VideoRepositoryImpl implements VideoRepository {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void saveVideo(Video record) {
        videoMapper.insertSelective(record);
    }
}
