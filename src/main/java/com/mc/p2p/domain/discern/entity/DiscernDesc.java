package com.mc.p2p.domain.discern.entity;

import lombok.Data;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Data
public class DiscernDesc {

    /**
     * 描述
     */
    private String description;

    /**
     * 百科链接
     */
    private String baike_url;

    /**
     * 图片链接
     */
    private String image_url;
}
