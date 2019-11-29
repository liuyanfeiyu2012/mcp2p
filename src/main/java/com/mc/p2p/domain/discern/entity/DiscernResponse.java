package com.mc.p2p.domain.discern.entity;

import lombok.Data;

import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/28 10:35 PM
 */
@Data
public class DiscernResponse {

    /**
     * 识图结果
     */
    private List<DiscernItem> result;
}
