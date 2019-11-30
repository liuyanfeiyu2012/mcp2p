package com.mc.p2p.domain.discern.entity;

import lombok.Data;

/**
 * @auther: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Data
public class DiscernDo {

    /**
     * 名称
     */
    private String name;

    /**
     * 打分
     */
    private String score;

    /**
     * 描述
     */
    private String description;

    /**
     * 构造方法
     *
     * @param name        名称
     * @param score       得分
     * @param description 描述
     */
    public DiscernDo(String name, String score, String description) {
        this.name = name;
        this.score = score;
        this.description = description;
    }
}
