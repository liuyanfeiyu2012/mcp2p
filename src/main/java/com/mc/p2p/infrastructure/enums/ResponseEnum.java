package com.mc.p2p.infrastructure.enums;

import lombok.Getter;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Getter
public enum ResponseEnum {

    /**
     * 5001
     */
    NOT_SUPPORT_ACTION("5001", "不支持的文件操作"),

    /**
     * 5002
     */
    FILE_NULL("5002", "文件不存在"),

    /**
     * 5003
     */
    FILE_STORAGE_ERR("5003", "文件存储异常"),

    /**
     * 5004
     */
    FILE_UPLOAD_ERR("5004", "文件上传失败, 请重试"),

    /**
     * 5005
     */
    VIDEO_MAX_TIME_LIMIT("5005", "亲, 上传视频最大支持15秒奥~"),

    /**
     * 5006
     */
    LOGIN_LIMIT("5006", "亲, 请登录~"),

    /**
     * 5007
     */
    COMMENT_PARSE_ERR("5007", "评论解析异常"),

    /**
     * 5008
     */
    COMMENT_VIDEO_NULL("5008", "评论视频不存在"),

    /**
     * 5010
     */
    VOICE_COMMENT_CAN_NOT_RECOGNIZED("5010", "亲，语音无法解析，请再试一次"),

    /**
     * 5011
     */
    NOT_ANIMAL("5011", "本站是萌宠社区, 只支持上传动物视频~"),

    /**
     * 5012
     */
    VIDEO_MIN_TIME_LIMIT("5012", "亲, 上传视频最小3秒奥~");

    /**
     * code
     */
    private String code;

    /**
     * desc
     */
    private String desc;

    ResponseEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
