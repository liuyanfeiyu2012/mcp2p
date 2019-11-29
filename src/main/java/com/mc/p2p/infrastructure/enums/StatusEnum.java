package com.mc.p2p.infrastructure.enums;

import lombok.Getter;

/**
 * @author : Yuan.Pan 2019/11/29 10:20 PM
 */
@Getter
public enum StatusEnum {
    DISABLE("0", "不可用"),
    ENABLE("1", "可用");

    StatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;
}
