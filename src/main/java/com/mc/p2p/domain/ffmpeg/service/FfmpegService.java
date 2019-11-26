package com.mc.p2p.domain.ffmpeg.service;

import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;

/**
 * @author : Yuan.Pan 2019/11/24 5:15 PM
 */
public interface FfmpegService {

    /**
     * video handle
     *
     * @param request {param-0: video domain obj}
     */
    void videoFilter(FfmpegDo request) throws Exception;

    /**
     * comment handle
     *
     * @param request {param-0: comment}
     * @throws Exception {e}
     */
    void commentFilter(FfmpegDo request) throws Exception;

}
