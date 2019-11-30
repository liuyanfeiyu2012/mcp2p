package com.mc.p2p.domain.discern.entity;

import lombok.Data;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
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
