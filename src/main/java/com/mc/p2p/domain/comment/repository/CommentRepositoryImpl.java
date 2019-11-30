package com.mc.p2p.domain.comment.repository;

import com.mc.p2p.mapper.CommentMapper;
import com.mc.p2p.model.po.Comment;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    /**
     * 评论Mapper
     */
    @Resource
    private CommentMapper commentMapper;

    /**
     * 保存评论
     *
     * @param comment 评论
     */
    @Override
    public void saveComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    /**
     * 获取评论列表
     * @param videoId 视频编号
     * @return 评论列表
     */
    @Override
    public List<Comment> getCommentList(String videoId) {
        final Comment comment = new Comment();
        comment.setVideoId(videoId);
        return commentMapper.select(comment);
    }


}
