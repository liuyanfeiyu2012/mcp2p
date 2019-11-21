package com.mc.p2p.controller;

import com.google.common.collect.Lists;
import com.mc.p2p.common.RespVo;
import com.mc.p2p.vo.BgmQueryResp;
import com.mc.p2p.vo.UploadReq;
import com.mc.p2p.vo.VideoQueryResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/21 10:21 AM
 */
@Api(tags = "视频接口API")
@RequestMapping("/video")
@RestController
public class VideoController {

    @ApiOperation("视频上传接口")
    @PostMapping("/upload")
    public RespVo upload(@Valid @RequestBody UploadReq request, @ApiParam(name = "上传的文件对象") MultipartFile file) {
        return RespVo.SUCCESS();
    }

    @ApiOperation("拉取背景音乐列表")
    @GetMapping("/bgm-list")
    public RespVo<List<BgmQueryResp>> selectBgmList() {
        return RespVo.SUCCESS(Lists.newArrayList());
    }

    @ApiOperation("查询视频列表")
    @GetMapping("/list")
    public RespVo<List<VideoQueryResp>> selectVideo() {
        return RespVo.SUCCESS(Lists.newArrayList());
    }
}
