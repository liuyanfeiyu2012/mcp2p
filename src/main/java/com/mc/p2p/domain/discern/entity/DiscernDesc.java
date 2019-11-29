package com.mc.p2p.domain.discern.entity;

import lombok.Data;

/**
 * @author : Yuan.Pan 2019/11/28 10:39 PM
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
