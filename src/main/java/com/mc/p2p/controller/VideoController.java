package com.mc.p2p.controller;

import com.alibaba.druid.util.StringUtils;
import com.mc.p2p.domain.comment.service.CommentService;
import com.mc.p2p.domain.discern.service.DiscernService;
import com.mc.p2p.domain.video.service.VideoService;
import com.mc.p2p.infrastructure.common.RespVo;
import com.mc.p2p.model.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @auther: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Api(tags = "视频接口API")
@Validated
@RequestMapping("/video")
@RestController
public class VideoController {

    @Resource
    private VideoService videoService;

    @Resource
    private CommentService commentService;

    @Resource
    private DiscernService discernService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("视频上传接口")
    @PostMapping("/upload")
    public RespVo upload(@Valid VideoUploadReq request,
                         @ApiParam(name = "上传的文件对象") MultipartFile file) {
        videoService.saveVideo(request, file);
        return RespVo.SUCCESS();
    }

    @ApiOperation("拉取背景音乐列表")
    @GetMapping("/bgm-list")
    public RespVo<List<BgmQueryResp>> selectBgmList() {
        return RespVo.SUCCESS(videoService.selectBgmList());
    }

    @ApiOperation("查询视频列表")
    @GetMapping("/list")
    public RespVo<List<VideoQueryResp>> selectVideo(@ApiParam(name = "用戶编号") String uid) {
        List<VideoQueryResp> resp = videoService.selectVideoList();
        if (!StringUtils.isEmpty(uid)) {
            //按照是否看過視頻排序
            Set<Object> sets = redisTemplate.opsForSet().members(uid);
            List<VideoQueryResp> firstList = new LinkedList<>();
            List<VideoQueryResp> secondList = new LinkedList<>();
            for (VideoQueryResp item : resp) {
                if (sets.contains(item.getVideoId())) {
                    secondList.add(item);
                } else {
                    firstList.add(item);
                }
            }
            firstList.addAll(secondList);
            return RespVo.SUCCESS(firstList);
        }

        return RespVo.SUCCESS(resp);
    }

    @ApiOperation("用户评论")
    @PostMapping("/comment")
    public RespVo comment(@Valid CommentReq request,
                          @ApiParam(name = "上传语音文件") MultipartFile file) {
        commentService.comment(request, file);
        return RespVo.SUCCESS();
    }

    @ApiOperation("获取评论列表")
    @GetMapping("/comment-list")
    public RespVo<List<CommentListQueryResp>> getCommentList(@ApiParam(name = "视频编号")
                                                                 @NotBlank String videoId) {
        return RespVo.SUCCESS(commentService.getCommentList(videoId));
    }

    @ApiOperation("智能识别")
    @GetMapping("/ai")
    public RespVo ai(@ApiParam(name = "视频编号") @NotBlank String path) {
        discernService.discern(path);
        return RespVo.SUCCESS();
    }

    @ApiOperation("看过视频标记")
    @PostMapping("/video-viewed")
    public RespVo viewed(@Valid VideoViewedReq request) {
        redisTemplate.opsForSet().add(request.getUid(), request.getVideoId());
        return RespVo.SUCCESS();
    }
}
