package com.mc.p2p.infrastructure.constant;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.AudioFormat;
import java.io.File;
import java.util.List;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Slf4j
public class McConstant {

    /**
     * AI_APP_ID
     */
    public static final String AI_APP_ID = "17884831";

    /**
     * AI_API_KEY
     */
    public static final String AI_API_KEY = "sEIZGTv1n7jaydDxPAyYxZUh";

    /**
     * AI_SECRET_KEY
     */
    public static final String AI_SECRET_KEY
            = "g14xPYs0uQpOTlvuO1STyFe9GmR3cGuP";

    /**
     * FILE_VIDEO_PATH
     */
    public static final String FILE_VIDEO_PATH
            = "/home/ubuntu/mcp2p/file/video/";

    /**
     * FILE_VOICE_PATH
     */
    public static final String FILE_VOICE_PATH
            = "/home/ubuntu/mcp2p/file/voice/";

    /**
     * FILE_BG_PATH
     */
    public static final String FILE_BG_PATH
            = "/home/ubuntu/mcp2p/file/bg/";

    /**
     * FILE_BGM_PATH
     */
    public static final String FILE_BGM_PATH
            = "/home/ubuntu/mcp2p/file/bgm/";

    /**
     * FILE_DISCERN_PATH
     */
    public static final String FILE_DISCERN_PATH
            = "/home/ubuntu/mcp2p/file/ai/";

    /**
     * FFMPEG_DO_KEY
     */
    public static final String FFMPEG_DO_KEY = "FFPEG_DO_KEY";

    /**
     * CONVERT_KEY
     */
    public static final String CONVERT_KEY = "CONVERT_KEY";

    /**
     * MIX_BGM_KEY
     */
    public static final String MIX_BGM_KEY = "MIX_BGM_KEY";

    /**
     * CANCEL_BGM_KEY
     */
    public static final String CANCEL_BGM_KEY = "CANCEL_BGM_KEY";

    /**
     * SCREENSHOT_KEY
     */
    public static final String SCREENSHOT_KEY = "SCREENSHOT_KEY";

    /**
     * COMPRESS_KEY
     */
    public static final String COMPRESS_KEY = "COMPRESS_KEY";

    /**
     * ADD_WATER_KEY
     */
    public static final String ADD_WATER_KEY = "ADD_WATER_KEY";

    /**
     * MP4_EXT
     */
    public static final String MP4_EXT = ".mp4";

    /**
     * JPG_EXT
     */
    public static final String JPG_EXT = ".jpg";

    /**
     * WAV_EXT
     */
    public static final String WAV_EXT = ".wav";

    /**
     * MAX_LIMIT
     */
    public static final double MAX_LIMIT = 20;

    /**
     * MIN_LIMIT
     */
    public static final double MIN_LIMIT = 4;

    /**
     * VIDEO_NGINX_PREFFIX
     */
    public static final String VIDEO_NGINX_PREFFIX
            = "https://www.mengchongp2p.online/video/";

    /**
     * BG_NGINX_PREFFIX
     */
    public static final String BG_NGINX_PREFFIX
            = "https://www.mengchongp2p.online/bg/";

    /**
     * CAT_PRODUCT
     */
    public static final List<String> CAT_PRODUCT = Lists.newArrayList("猫罐头",
            "进口猫粮",
            "猫砂",
            "逗猫棒",
            "猫猫小鱼干");

    /**
     * DOG_PRODUCT
     */
    public static final List<String> DOG_PRODUCT =
            Lists.newArrayList("进口狗粮", "狗狗磨牙棒",
                    "狗星人飞盘", "小狗皮球");

    /**
     * FISH_PRODUCT
     */
    public static final List<String> FISH_PRODUCT = Lists.newArrayList(
            "鱼食", "小鱼纪念品", "鱼竿", "钓鱼套装");

    /**
     * NOMAL_PRODUCT
     */
    public static final List<String> NOMAL_PRODUCT = Lists.newArrayList(
            "掌上商城精品推荐A", "掌上商城精品推荐B",
            "掌上商城精品推荐C", "掌上商城精品推荐D");

    /**
     * AI_SC_NUM
     */
    public static final int AI_SC_NUM = 3;

    /**
     * CATE_A
     */
    public static final String CATE_A = "猫";

    /**
     * DOG_A
     */
    public static final String DOG_A = "柯基";

    /**
     * DOG_B
     */
    public static final String DOG_B = "哈士奇";

    /**
     * DOG_C
     */
    public static final String DOG_C = "阿拉斯加";

    /**
     * DOG_D
     */
    public static final String DOG_D = "萨摩耶";

    /**
     * DOG_E
     */
    public static final String DOG_E = "拉布拉多";

    /**
     * DOG_F
     */
    public static final String DOG_F = "吉娃娃";

    /**
     * DOG_G
     */
    public static final String DOG_G = "泰迪";

    /**
     * DOG_H
     */
    public static final String DOG_H = "梗";

    /**
     * DOG_I
     */
    public static final String DOG_I = "獒";

    /**
     * DOG_J
     */
    public static final String DOG_J = "狗";

    /**
     * DOG_K
     */
    public static final String DOG_K = "犬";

    /**
     * FISH_A
     */
    public static final String FISH_A = "鱼";

    /**
     * NO_ANIMAL
     */
    public static final String NO_ANIMAL = "非动物";

    /**
     * SAMPLE_RATE
     */
    public static final int SAMPLE_RATE = 16000;

    /**
     * BYTES_PER_SAMPLE
     */
    private static final int BYTES_PER_SAMPLE = 2;

    /**
     * BITS_PER_SAMPLE
     */
    private static final int BITS_PER_SAMPLE = 16;

    /**
     * MONO
     */
    private static final int MONO = 1;

    /**
     * LITTLE_ENDIAN
     */
    private static final boolean LITTLE_ENDIAN = false;

    /**
     * AUDIO_FORMAT
     */
    public static final AudioFormat AUDIO_FORMAT = new AudioFormat(
            AudioFormat.Encoding.PCM_SIGNED,
            SAMPLE_RATE,
            BITS_PER_SAMPLE,
            MONO,
            BYTES_PER_SAMPLE,
            8000,
            LITTLE_ENDIAN);

    /**
     * 0-2分
     */
    public static final String ZERO_TWO = "打扰了(￣^￣)";

    /**
     * 3-4分
     */
    public static final String THREE_FOUR = "emmm( ´Д`)";

    /**
     * 5-6
     */
    public static final String FIVE_SIX = "吃瓜٩(๑´0`๑)۶";

    /**
     * 7-8
     */
    public static final String SEVEN_EIGHT = "哎吆，不错哦(•‾̑⌣‾̑•)";

    /**
     * 9-10
     */
    public static final String NINE_TEN = "爱了(//▽//)";

    public McConstant() {
        String mk = "mkdirs-{}";
        // video root path
        File videoPath = new File(FILE_VIDEO_PATH);
        if (!videoPath.exists()) {
            boolean mkdirs = videoPath.mkdirs();
            log.info(mk, mkdirs);
        }

        // voice root path
        File voicePath = new File(FILE_VOICE_PATH);
        if (!voicePath.exists()) {
            boolean mkdirs = voicePath.mkdirs();
            log.info(mk, mkdirs);
        }

        // bg root path
        File bgPath = new File(FILE_BG_PATH);
        if (!bgPath.exists()) {
            boolean mkdirs = bgPath.mkdirs();
            log.info(mk, mkdirs);
        }

        // bgm root path
        File bgmPath = new File(FILE_BGM_PATH);
        if (!bgmPath.exists()) {
            boolean mkdirs = bgmPath.mkdirs();
            log.info(mk, mkdirs);
        }

        // ai animal
        File aiPath = new File(FILE_DISCERN_PATH);
        if (!aiPath.exists()) {
            boolean mkdirs = aiPath.mkdirs();
            log.info(mk, mkdirs);
        }
    }
}
