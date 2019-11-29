package com.mc.p2p.infrastructure.constant;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import javax.sound.sampled.AudioFormat;
import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * @author : Yuan.Pan 2019/11/23 10:17 AM
 */
public class McConstant {

    public static final String AI_APP_ID = "17884831";
    public static final String AI_API_KEY = "sEIZGTv1n7jaydDxPAyYxZUh";
    public static final String AI_SECRET_KEY = "g14xPYs0uQpOTlvuO1STyFe9GmR3cGuP";

    public static final String FILE_VIDEO_PATH = "/home/ubuntu/mcp2p/file/video/";
    public static final String FILE_VOICE_PATH = "/home/ubuntu/mcp2p/file/voice/";
    public static final String FILE_BG_PATH = "/home/ubuntu/mcp2p/file/bg/";
    public static final String FILE_BGM_PATH = "/home/ubuntu/mcp2p/file/bgm/";
    public static final String FILE_DISCERN_PATH = "/home/ubuntu/mcp2p/file/ai/";

    public static final String FFMPEG_DO_KEY = "FFPEG_DO_KEY";
    public static final String CONVERT_KEY = "CONVERT_KEY";
    public static final String MIX_BGM_KEY = "MIX_BGM_KEY";
    public static final String CANCEL_BGM_KEY = "CANCEL_BGM_KEY";
    public static final String SCREENSHOT_KEY = "SCREENSHOT_KEY";
    public static final String COMPRESS_KEY = "COMPRESS_KEY";

    public static final String BGM_NGINX_PREFFIX = "https://www.mengchongp2p.online/bgm/";
    public static final String VIDEO_NGINX_PREFFIX = "https://www.mengchongp2p.online/video/";
    public static final String BG_NGINX_PREFFIX = "https://www.mengchongp2p.online/bg/";

    public static final List<String> CAT_PRODUCT = Lists.newArrayList( "萌宠小瓶干", "精品猫粮", "喵星人小皮球", "萌宠挂件");
    public static final List<String> DOG_PRODUCT = Lists.newArrayList( "精品狗粮", "小狗宠物挂件", "狗星人飞盘", "小狗萌宠挂件");
    public static final List<String> FISH_PRODUCT = Lists.newArrayList( "鱼食", "小鱼纪念品", "鱼竿", "钓鱼套装");
    public static final List<String> NOMAL_PRODUCT = Lists.newArrayList( "品质电商精品推荐A", "品质电商精品推荐B", "品质电商精品推荐C", "品质电商精品推荐D");

    public static final int AI_SC_NUM = 3;


    //设置APPID/AK/SK
    public static final String APP_ID = "17832684";
    public static final String API_KEY = "uYore5u1woXsi60jOtA0M4mE";
    public static final String SECRET_KEY = "o1WrqtVVAoeRzIxByYK50p6C38RbM35d";


    public static final int SAMPLE_RATE = 16000;
    public static final Integer SuccessCode = 0;
    // 16-bit audio
    private static final int BYTES_PER_SAMPLE = 2;
    // 16-bit audio
    private static final int BITS_PER_SAMPLE = 16;
    private static final double MAX_16_BIT = 32768;
    private static final int SAMPLE_BUFFER_SIZE = 4096;
    private static final int MONO   = 1;
    private static final int STEREO = 2;
    private static final boolean LITTLE_ENDIAN = false;
    private static final boolean BIG_ENDIAN    = true;
    private static final boolean SIGNED        = true;
    private static final boolean UNSIGNED      = false;
    public static AudioFormat DSTFORMAT = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
            SAMPLE_RATE,
            BITS_PER_SAMPLE,
            MONO,
            BYTES_PER_SAMPLE,
            8000,
            LITTLE_ENDIAN);


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

        // ai animal
        File aiPath = new File(FILE_DISCERN_PATH);
        if (!aiPath.exists()) {
            boolean mkdirs = aiPath.mkdirs();
        }
    }
}
