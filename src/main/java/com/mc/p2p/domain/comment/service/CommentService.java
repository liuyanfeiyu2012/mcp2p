package com.mc.p2p.domain.comment.service;

import com.mc.p2p.model.vo.CommentReq;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Yuan.Pan 2019/11/26 8:39 PM
 */
public interface CommentService {

    void comment(CommentReq request, MultipartFile file);
}
