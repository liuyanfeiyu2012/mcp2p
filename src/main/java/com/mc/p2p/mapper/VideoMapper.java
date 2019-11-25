package com.mc.p2p.mapper;

import com.mc.p2p.model.po.Video;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface VideoMapper extends Mapper<Video>, MySqlMapper<Video> {
}