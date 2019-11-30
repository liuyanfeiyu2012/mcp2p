package com.mc.p2p.domain.video.repository;

import com.mc.p2p.model.po.Bgm;

import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/25 1:52 PM
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
