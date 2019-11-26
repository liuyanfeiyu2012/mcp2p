package com.mc.p2p.domain.video.repository;

import com.google.common.collect.Lists;
import com.mc.p2p.mapper.VideoMapper;
import com.mc.p2p.model.po.Video;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

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

    @Override
    public List<Video> selectList(String openId) {
        Video record = new Video();
        if (StringUtils.isNotBlank(openId)) {
            record.setUid(openId);
        }

        List<Video> videoList = videoMapper.select(record);
        if (CollectionUtils.isEmpty(videoList)) {
            return Lists.newArrayList();
        }

        Collections.shuffle(videoList);
        return videoList;
    }

    @Override
    public Integer likeCount(String openId) {
        return videoMapper.likeCount(openId);
    }

    @Override
    public Video selectOne(String videoId) {
        return videoMapper.selectByPrimaryKey(videoId);
    }
}
