package com.mc.p2p.mapper;

import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.CityCircleResp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface VideoMapper extends Mapper<Video>, MySqlMapper<Video> {

    /**
     * 计算偷食数
     *
     * @param openId 用户编号
     * @return 用户投食数
     */
    @Select("SELECT SUM(like_count) FROM video WHERE uid = #{openId}")
    Integer likeCount(@Param("openId") String openId);

    @Select("SELECT city, count(1) as hot FROM video where status = 1 group by city")
    List<CityCircleResp> circle();
}