package com.mc.p2p.infrastructure.enums;

import lombok.Getter;

/**
 * @author : Yuan.Pan 2019/11/23 10:47 AM
 */
@Getter
public enum FfmpegTypeEnum {
    CONVERT_VIDEO("1", "转换视频", "ffmpeg -i %s -y %s"),
    CANCEL_BGM("2", "消除音频", "ffmpeg -i %s -c:v copy -an %s"),
    CONVERT_VOICE("3", "转换声音", "ffmpeg -i %s -f wav %s"),
    MIX_BGM("4", "融合背景音乐", "ffmpeg -i %s -i %s -t %s -y %s"),
    SCREENSHOT("5", "截图", "ffmpeg -ss 00:00:01 -y -i %s -vframes 1 %s"),
    COMPRESS_VIDEO("6", "压缩视频", "ffmpeg -i %s -b:v 650k -strict -2 -y %s"),
    AI_SCREENSHOT("7", "截图智能识别", "ffmpeg -ss %s -y -i %s -vframes 1 %s"),
    ADD_WATER("8", "添加水印", "ffmpeg -i %s -i /tmp/ubuntu/mcp2p/file/logo/logo.png -filter_complex overlay=5:5 -codec:a copy %s");

    private String code;
    private String desc;
    private String command;

    FfmpegTypeEnum(String code, String desc, String command) {
        this.code = code;
        this.desc = desc;
        this.command = command;
    }
}
