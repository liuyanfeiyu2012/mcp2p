package com.mc.p2p.domain.video.service;

import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.BgmQueryResp;
import com.mc.p2p.model.vo.VideoQueryResp;
import com.mc.p2p.model.vo.VideoUploadReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/23 9:07 AM
 */
public interface VideoService {

    /**
     * save video file
     *
     * @param request {param-0: upload video req}
     * @param file    {param-1: file}
     */
    void saveVideo(VideoUploadReq request, MultipartFile file);

    /**
     * select bgm list
     *
     * @return {bgm list}
     */
    List<BgmQueryResp> selectBgmList();

    /**
     * select video list
     *
     * @return {video list}
     */
    List<VideoQueryResp> selectVideoList();

    /**
     * select video list
     *
     * @param openId {param-0: openId}
     * @return {video list}
     */
    List<VideoQueryResp> selectVideoList(String openId);

    /**
     * select user like count total
     *
     * @param openId {param-0: openId}
     * @return {count}
     */
    Integer likeCount(String openId);

    /**
     * select by video id
     *
     * @param videoId {param-0: video id}
     * @return {video}
     */
    Video selectOne(String videoId);
}
