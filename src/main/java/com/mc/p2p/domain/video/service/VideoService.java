package com.mc.p2p.domain.video.service;

import com.mc.p2p.model.vo.VideoUploadReq;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Yuan.Pan 2019/11/23 9:07 AM
 */
public interface VideoService {

    /**
     * save video file
     *
     * @param request {param-0: upload video req}
     * @param file {param-1: file}
     */
    void saveVideo(VideoUploadReq request, MultipartFile file);
}
