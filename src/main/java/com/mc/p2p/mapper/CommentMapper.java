package com.mc.p2p.mapper;

import com.mc.p2p.model.po.Comment;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface CommentMapper extends Mapper<Comment>, MySqlMapper<Comment> {
}