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
    void videoFilter(FfmpegDo request);

    /**
     * handle file pic
     *
     * @param request {param-0: request}
     */
    void videoPicFilter(FfmpegDo request);

    /**
     * comment handle
     *
     * @param request {param-0: comment}
     */
    void commentFilter(FfmpegDo request);

}
