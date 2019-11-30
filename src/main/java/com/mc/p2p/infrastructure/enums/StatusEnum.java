package com.mc.p2p.infrastructure.enums;

import lombok.Getter;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
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
