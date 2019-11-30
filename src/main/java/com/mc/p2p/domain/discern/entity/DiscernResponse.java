package com.mc.p2p.domain.discern.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Data
public class DiscernResponse {

    /**
     * 识图结果
     */
    private List<DiscernItem> result;
}
