package com.mc.p2p.domain.comment.repository;

import com.mc.p2p.model.po.Comment;

public interface CommentRepository {

    void saveComment(Comment comment);
}
