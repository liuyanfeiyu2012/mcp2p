package com.mc.p2p.domain.comment.service;

import com.mc.p2p.model.vo.CommentListQueryResp;
import com.mc.p2p.model.vo.CommentReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
public interface CommentService {

    /**
     * 评论
     *
     * @param request 请求参数
     * @param file    语音文件
     */
    void comment(CommentReq request, MultipartFile file);

    /**
     * 获取评论列表
     * @param videoId 视频编号
     * @return 评论列表
     */
    List<CommentListQueryResp> getCommentList(String videoId);
}
