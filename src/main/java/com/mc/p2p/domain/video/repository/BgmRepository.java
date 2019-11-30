package com.mc.p2p.domain.video.repository;

import com.mc.p2p.model.po.Bgm;

import java.util.List;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
public interface BgmRepository {

    /**
     * 获取列表
     * @return 文件
     */
    List<Bgm> selectList();

    /**
     * 获取文件
     * @param bgmId 文件编号
     * @return 文件
     */
    Bgm selectBgm(String bgmId);
}
