package com.mc.p2p.mapper;

import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.CityCircleResp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface VideoMapper extends Mapper<Video>, MySqlMapper<Video> {

    @Select("SELECT SUM(like_count) FROM video WHERE uid = #{openId}")
    Integer likeCount(@Param("openId") String openId);

    @Select("SELECT city, count(1) as hot FROM video group by city where status = 1")
    List<CityCircleResp> circle();
}