package com.mc.p2p.mapper;

import com.mc.p2p.model.po.Bgm;

public interface bgmMapper {
    int deleteByPrimaryKey(String bgmId);

    int insert(Bgm record);

    int insertSelective(Bgm record);

    Bgm selectByPrimaryKey(String bgmId);

    int updateByPrimaryKeySelective(Bgm record);

    int updateByPrimaryKey(Bgm record);
}