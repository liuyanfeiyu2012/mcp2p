package com.mc.p2p.domain.comment.service;

import com.mc.p2p.domain.comment.entity.CommentDo;
import com.mc.p2p.domain.comment.entity.VoiceDo;
import com.mc.p2p.domain.comment.repository.CommentRepository;
import com.mc.p2p.domain.customer.service.CustomerService;
import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.domain.ffmpeg.service.FfmpegService;
import com.mc.p2p.domain.video.service.VideoService;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.enums.SentimentEnum;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.mc.p2p.model.po.Comment;
import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.CommentListQueryResp;
import com.mc.p2p.model.vo.CommentReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Yuan.Pan 2019/11/26 8:39 PM
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    /**
     * 文件处理
     */
    @Resource
    private FfmpegService ffmpegService;

    /**
     * 用户服务
     */
    @Resource
    private CustomerService customerService;

    /**
     * 视频服务
     */
    @Resource
    private VideoService videoService;

    /**
     * 评论接口
     */
    @Resource
    private CommentRepository commentRepository;

    /**
     * 评论
     *
     * @param request 请求参数
     * @param file    语音文件
     */
    @Override
    public void comment(CommentReq request, MultipartFile file) {
        Video video = videoService.selectOne(request.getVideoId());
        Customer customer = customerService.selectByOpenId(request.getUid());

        CommentDo commentDo = new CommentDo(customer, video, file);
        commentDo.storage();

        FfmpegDo ffmpegDo = new FfmpegDo(commentDo.getFilePath(),
                commentDo.getFileId());
        try {
            ffmpegService.commentFilter(ffmpegDo);
        } catch (Exception e) {
            log.error("评论存储异常 e-{}", e);
            throw new BusinessException(ResponseEnum.COMMENT_PARSE_ERR);
        }
        log.info("文件处理结束");
        VoiceDo voiceDo = new VoiceDo(ffmpegDo.getTargetFile(),
                ffmpegDo.getFileId());
        voiceDo.setComment();
        saveCmment(request, voiceDo, customer);
    }

    /**
     * 保存评论
     *
     * @param req      请求参数
     * @param voiceDo  音频参数
     * @param customer 用户信息
     */
    private void saveCmment(CommentReq req, VoiceDo voiceDo, Customer customer) {
        Comment comment = new Comment();
        comment.setVideoId(req.getVideoId());
        comment.setUid(req.getUid());
        comment.setUname(customer.getWxName());
        comment.setAvatar(customer.getAvatar());
        comment.setCommentId(voiceDo.getFileId());
        comment.setVoicePath(voiceDo.getVoicePath());
        comment.setContext(voiceDo.getSpeechData().getResult());
        comment.setScore(voiceDo.getScore());
        comment.setSentiment(voiceDo.getNlpData().getSentiment());
        comment.setNegative(String.valueOf(voiceDo.getNlpData().getNegative()));
        comment.setPositive(String.valueOf(voiceDo.getNlpData().getPositive()));
        commentRepository.saveComment(comment);
    }

    /**
     * 获取评论列表
     * @param videoId 视频编号
     * @return 评论列表
     */
    @Override
    public List<CommentListQueryResp> getCommentList(String videoId) {
        List<Comment> commentList = commentRepository.getCommentList(videoId);
        return commentList.stream()
                .map(comment -> CommentListQueryResp.builder()
                        .userId(comment.getUid())
                        .userName(comment.getUname())
                        .avatar(comment.getAvatar())
                        .context(comment.getContext())
                        .sentiment(SentimentEnum.findChineseType(comment
                                .getScore()))
                        .score(String.valueOf(comment.getScore()))
                        .commentTime(comment.getCommentTime())
                        .build())
                .sorted(Comparator.comparing(
                        CommentListQueryResp::getCommentTime).reversed())
                .collect(Collectors.toList());
    }
}
