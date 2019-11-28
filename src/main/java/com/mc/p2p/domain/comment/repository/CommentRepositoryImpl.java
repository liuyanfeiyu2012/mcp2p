package com.mc.p2p.domain.comment.repository;

import com.mc.p2p.mapper.CommentMapper;
import com.mc.p2p.model.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void saveComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    @Override
    public List<Comment> getCommentList(String videoId) {
        Comment comment = new Comment();
        comment.setVideoId(videoId);
        return commentMapper.select(comment);
    }


}
