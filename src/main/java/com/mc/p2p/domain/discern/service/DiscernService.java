package com.mc.p2p.domain.discern.service;

import com.mc.p2p.domain.discern.entity.DiscernDo;

/**
 * @author : Yuan.Pan 2019/11/28 8:52 PM
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
