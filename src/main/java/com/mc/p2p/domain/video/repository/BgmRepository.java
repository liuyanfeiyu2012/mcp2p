package com.mc.p2p.domain.video.repository;

import com.mc.p2p.model.po.Bgm;

import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/25 1:52 PM
 */
public interface BgmRepository {

    List<Bgm> selectList();

    String selectBgmUri(String bgmId);
}
