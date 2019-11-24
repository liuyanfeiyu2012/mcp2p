package com.mc.p2p.infrastructure.enums;

import lombok.Getter;

/**
 * @author : Yuan.Pan 2019/11/23 11:15 AM
 */
@Getter
public enum ResponseEnum {
    NOT_SUPPORT_ACTION("5001", "不支持的文件操作"),
    FILE_NULL("5002", "文件不存在"),
    FILE_STORAGE_ERR("5003", "文件存储异常"),
    FILE_UPLOAD_ERR("5004", "文件上传失败, 请重试");

    private String code;
    private String desc;

    ResponseEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
