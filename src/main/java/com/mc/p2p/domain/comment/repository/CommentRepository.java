package com.mc.p2p.domain.comment.repository;

import com.mc.p2p.model.po.Comment;

import java.util.List;

public interface CommentRepository {

    void saveComment(Comment comment);

    List<Comment> getCommentList(String videoId);
}
