package com.mc.p2p.infrastructure.constant;

import java.io.File;

/**
 * @author : Yuan.Pan 2019/11/23 10:17 AM
 */
public class McConstant {

    public static final String FILE_VIDEO_PATH = "/home/ubuntu/mcp2p/file/video/";
    public static final String FILE_VOICE_PATH = "/home/ubuntu/mcp2p/file/voice/";
    public static final String FILE_BG_PATH = "/home/ubuntu/mcp2p/file/bg/";
    public static final String FILE_BGM_PATH = "/home/ubuntu/mcp2p/file/bgm/";

    public static final String FFMPEG_DO_KEY = "FFPEG_DO_KEY";
    public static final String CONVERT_KEY = "CONVERT_KEY";
    public static final String MIX_BGM_KEY = "MIX_BGM_KEY";
    public static final String CANCEL_BGM_KEY = "CANCEL_BGM_KEY";
    public static final String SCREENSHOT_KEY = "SCREENSHOT_KEY";

    public static final String BGM_NGINX_PREFFIX = "http://www.mengchongp2p.online/bgm/";
    public static final String VIDEO_NGINX_PREFFIX = "http://www.mengchongp2p.online/video/";
    public static final String BG_NGINX_PREFFIX = "http://www.mengchongp2p.online/bg/";

    static {
        // video root path
        File videoPath = new File(FILE_VIDEO_PATH);
        if (!videoPath.exists()) {
            boolean mkdirs = videoPath.mkdirs();
        }

        // voice root path
        File voicePath = new File(FILE_VOICE_PATH);
        if (!voicePath.exists()) {
            boolean mkdirs = voicePath.mkdirs();
        }

        // bg root path
        File bgPath = new File(FILE_BG_PATH);
        if (!bgPath.exists()) {
            boolean mkdirs = bgPath.mkdirs();
        }

        // bgm root path
        File bgmPath = new File(FILE_BGM_PATH);
        if (!bgmPath.exists()) {
            boolean mkdirs = bgmPath.mkdirs();
        }
    }
}
