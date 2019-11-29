package com.mc.p2p.controller;
import com.mc.p2p.domain.comment.service.CommentService;
import com.mc.p2p.domain.discern.service.DiscernService;
import com.mc.p2p.domain.video.service.VideoService;
import com.mc.p2p.infrastructure.common.RespVo;
import com.mc.p2p.model.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/21 10:21 AM
 */
@Api(tags = "视频接口API")
@Validated
@RequestMapping("/video")
@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private DiscernService discernService;

    @ApiOperation("视频上传接口")
    @PostMapping("/upload")
    public RespVo upload(@Valid VideoUploadReq request, @ApiParam(name = "上传的文件对象") MultipartFile file) {
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
    public RespVo<List<VideoQueryResp>> selectVideo() {
        return RespVo.SUCCESS(videoService.selectVideoList());
    }

    @ApiOperation("用户评论")
    @PostMapping("/comment")
    public RespVo comment(@Valid CommentReq request, @ApiParam(name = "上传语音文件") MultipartFile file) {
        commentService.comment(request, file);
        return RespVo.SUCCESS();
    }

    @ApiOperation("获取评论列表")
    @GetMapping("/comment-list")
    public RespVo<List<CommentListQueryResp>> getCommentList(@ApiParam(name ="视频编号")@NotBlank String videoId){
        return RespVo.SUCCESS(commentService.getCommentList(videoId));
    }

    @ApiOperation("测试识别")
    @GetMapping("/ai")
    public RespVo ai(@ApiParam(name ="视频编号")@NotBlank String path){
        discernService.discern(path);
        return RespVo.SUCCESS();
    }
}
