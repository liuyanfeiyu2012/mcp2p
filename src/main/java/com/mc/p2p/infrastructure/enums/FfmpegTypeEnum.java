package com.mc.p2p.infrastructure.enums;

import lombok.Getter;

/**
 * @author : Yuan.Pan 2019/11/23 10:47 AM
 */
@Getter
public enum FfmpegTypeEnum {
    CONVERT_VIDEO("1", "转换视频", "ffmpeg -i %s -y %s"),
    CANCEL_BGM("2", "消除音频", "ffmpeg -i %s -c:v copy -an %s"),
    CONVERT_VOICE("2", "转换声音", "ffmpeg -i %s -f wav %s"),
    MIX_BGM("3", "融合背景音乐", "ffmpeg -i %s -i %s -t 15 -y %s"),
    SCREENSHOT("4", "截图", "ffmpeg -ss 00:00:01 -y -i %s -vframes 1 %s");

    private String code;
    private String desc;
    private String command;

    FfmpegTypeEnum(String code, String desc, String command) {
        this.code = code;
        this.desc = desc;
        this.command = command;
    }
}
