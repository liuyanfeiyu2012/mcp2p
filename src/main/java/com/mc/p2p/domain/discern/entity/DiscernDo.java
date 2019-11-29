package com.mc.p2p.domain.discern.entity;

import lombok.Data;

/**
 * @author : Yuan.Pan 2019/11/29 10:20 AM
 */
@Data
public class DiscernDo {

    /** 名称 */
    private String name;

    /** 打分 */
    private String score;

    /** 描述 */
    private String description;

    public DiscernDo(String name, String score, String description) {
        this.name = name;
        this.score = score;
        this.description = description;
    }
}
