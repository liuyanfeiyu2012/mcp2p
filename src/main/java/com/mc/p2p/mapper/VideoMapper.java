package com.mc.p2p.mapper;

import com.mc.p2p.model.po.Video;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
public interface VideoMapper extends Mapper<Video>, MySqlMapper<Video> {

    /**
     * 计算偷食数
     *
     * @param openId 用户编号
     * @return 用户投食数
     */
    @Select("SELECT SUM(like_count) FROM video WHERE uid = #{openId}")
    Integer likeCount(@Param("openId") String openId);
}