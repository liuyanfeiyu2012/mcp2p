package com.mc.p2p.domain.ffmpeg.entity;
import lombok.Data;

/**
 * @author : Yuan.Pan 2019/11/25 10:42 AM
 */
@Data
public class VideoFfmDo {

    /** 视频背景图 */
    private String videoBgPath;

    /** 视频uri */
    private String videoPath;

    public VideoFfmDo(String videoPath) {
        this.videoPath = videoPath;
    }
}
