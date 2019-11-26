package com.mc.p2p.domain.comment.service;

import com.mc.p2p.domain.comment.entity.CommentDo;
import com.mc.p2p.domain.customer.service.CustomerService;
import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.domain.ffmpeg.service.FfmpegService;
import com.mc.p2p.domain.video.service.VideoService;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.CommentReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Yuan.Pan 2019/11/26 8:39 PM
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private FfmpegService ffmpegService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VideoService videoService;

    @Override
    public void comment(CommentReq request, MultipartFile file) {
        Video video = videoService.selectOne(request.getVideoId());
        Customer customer = customerService.selectByOpenId(request.getUid());

        CommentDo commentDo = new CommentDo(customer, video, file);
        commentDo.storage();

        FfmpegDo ffmpegDo = new FfmpegDo(commentDo.getFilePath(), commentDo.getFileId());
        try {
            ffmpegService.commentFilter(ffmpegDo);
        } catch (Exception e) {
            log.error("评论存储异常 e-{}", e);
            throw new BusinessException(ResponseEnum.COMMENT_PARSE_ERR);
        }
    }
}
