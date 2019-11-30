package com.mc.p2p.domain.discern.service;

import com.mc.p2p.domain.discern.entity.DiscernDo;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
public interface DiscernService {

    /**
     * 识图
     *
     * @param filePath 文件路径
     * @return 识图信息
     */
    DiscernDo discern(String filePath);
}
