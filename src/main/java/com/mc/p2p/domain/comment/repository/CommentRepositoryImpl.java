package com.mc.p2p.domain.comment.repository;

import com.mc.p2p.mapper.CommentMapper;
import com.mc.p2p.model.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void saveComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }
}
