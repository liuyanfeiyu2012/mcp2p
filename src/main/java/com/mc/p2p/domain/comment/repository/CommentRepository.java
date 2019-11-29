package com.mc.p2p.domain.comment.repository;

import com.mc.p2p.model.po.Comment;

import java.util.List;

/**
 * 评论接口类
 */
public interface CommentRepository {

    /**
     * 保存评论
     *
     * @param comment 评论
     */
    void saveComment(Comment comment);

    /**
     * 获取评论列表
     * @param videoId 视频编号
     * @return 评论列表
     */
    List<Comment> getCommentList(String videoId);
}
