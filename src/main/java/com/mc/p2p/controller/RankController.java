package com.mc.p2p.controller;

import com.google.common.collect.Lists;
import com.mc.p2p.infrastructure.common.RespVo;
import com.mc.p2p.mapper.VideoMapper;
import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.FeedReq;
import com.mc.p2p.model.vo.RankResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Auther: 谢星星
 * @Date: 2019/11/26 20:35
 * @Description:
 */
@Api(tags = "投食，排行API")
@Validated
@RequestMapping("/rank")
@RestController
public class RankController {
    private static final String RANK_PREFIX = "RANK_";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private VideoMapper videoMapper;

    @ApiOperation("投食+1")
    @PostMapping("/add")
    public RespVo add(@RequestBody FeedReq feedReq) {
        String key = RANK_PREFIX + feedReq.getVideoId() + "_" + feedReq.getOwnerId();
        redisTemplate.opsForValue().increment(key, 1);

        Integer score = (Integer) redisTemplate.opsForValue().get(key);
        Video video = new Video();
        video.setVideoId(feedReq.getVideoId());
        video.setLikeCount(score);

        videoMapper.updateByPrimaryKeySelective(video);
        return RespVo.SUCCESS();
    }

    @ApiOperation("总排行")
    @GetMapping(value = "/total-rank", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RespVo<List<RankResp>> totalRank() {
        Set<String> keys = redisTemplate.keys(RANK_PREFIX + "*");
        List<RankResp> restList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(keys)) {
            keys.forEach(key -> {
                String videoId = key.split("_")[1];
                Object res = redisTemplate.opsForValue().get(key);
                Integer score = (Integer) res;
                restList.add(new RankResp(videoId, null, score, null, null, null, null));

            });
        }

        Example example = Example.builder(Video.class)
                .where(WeekendSqls.<Video>custom().andIn(Video::getVideoId, restList.stream().map(RankResp::getVideoId).collect(Collectors.toList())))
                .build();

        List<Video> videos = videoMapper.selectByExample(example);
        Map<String, Video> videoMap = videos.stream().collect(Collectors.toMap(Video::getVideoId, v -> v));

        restList.forEach(rankResp -> {
            String vid = rankResp.getVideoId();
            Video video = videoMap.get(vid);
            if (video != null) {
                rankResp.setAvatar(video.getAvatar());
                rankResp.setWxName(video.getWxName());
                rankResp.setVideoUrl(video.getVideoUri());
                rankResp.setVideoBgUrl(video.getVideoBgUri());
                rankResp.setOwnerId(video.getUid());
            }
        });

        List<RankResp> newList = restList.stream().sorted(Comparator.comparing(RankResp::getScore).reversed()).collect(Collectors.toList());
        return RespVo.SUCCESS(newList);
    }
}
