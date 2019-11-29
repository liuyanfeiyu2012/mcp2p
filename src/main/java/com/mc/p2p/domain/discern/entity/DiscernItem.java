package com.mc.p2p.domain.discern.entity;

import lombok.Data;

/**
 * @author : Yuan.Pan 2019/11/28 9:40 PM
 */
@Data
public class DiscernItem {

    /**
     * 得分
     */
    private String score;

    /**
     * 名称
     */
    private String name;

    /**
     * 百科信息
     */
    private DiscernDesc baike_info;
}
