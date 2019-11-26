package com.mc.p2p.controller;

import com.google.common.collect.Lists;
import com.mc.p2p.infrastructure.common.RespVo;
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

import java.util.Comparator;
import java.util.List;
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
    private static final String RANK_PREFIX = "RANK-";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("投食+1")
    @PostMapping("/add")
    public RespVo add(@RequestBody FeedReq feedReq) {
        String key = RANK_PREFIX + feedReq.getVideoId() + "-" + feedReq.getOwnerId();
        redisTemplate.opsForValue().increment(key, 1);
        return RespVo.SUCCESS();
    }

    @ApiOperation("总排行")
    @GetMapping(value = "/total-rank", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RespVo<List<RankResp>> totalRank() {
        Set<String> keys = redisTemplate.keys(RANK_PREFIX + "*");
        List<RankResp> restList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(keys)) {

            keys.forEach(key -> {
                String videoId = key.split("-")[1];
                String ownerId = key.split("-")[2];
                Object res = redisTemplate.opsForValue().get(key);
                Integer score = (Integer) res;
                restList.add(new RankResp(videoId, ownerId, score));

            });
        }

        List<RankResp> newList = restList.stream().sorted(Comparator.comparing(RankResp::getScore).reversed()).collect(Collectors.toList());
        return RespVo.SUCCESS(newList);
    }
}
