package com.mc.p2p.domain.ffmpeg.service;

import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
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
