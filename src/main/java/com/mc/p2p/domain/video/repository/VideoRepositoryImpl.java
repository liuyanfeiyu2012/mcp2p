package com.mc.p2p.domain.video.repository;

import com.google.common.collect.Lists;
import com.mc.p2p.domain.discern.entity.DiscernDo;
import com.mc.p2p.infrastructure.enums.StatusEnum;
import com.mc.p2p.mapper.VideoMapper;
import com.mc.p2p.model.po.Video;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/24 10:41 PM
 */
@Slf4j
@Repository
public class VideoRepositoryImpl implements VideoRepository {

    @Resource
    private VideoMapper videoMapper;

    @Override
    public void saveVideo(Video record) {
        videoMapper.insertSelective(record);
    }

    @Override
    public void updateVideo(Video record) {
        videoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Video> selectList(String openId) {
        Video record = new Video();
        if (StringUtils.isNotBlank(openId)) {
            record.setUid(openId);
        }

        record.setStatus(StatusEnum.ENABLE.getCode());
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

    @Override
    public void saveDiscern(DiscernDo record, String videoId) {
        if (null == record) {
            return;
        }

        Video video = new Video();
        video.setVideoId(videoId);
        video.setScore(record.getScore());
        video.setAnimal(record.getName());
        video.setMemo(record.getDescription());
        videoMapper.updateByPrimaryKeySelective(video);
    }
}
