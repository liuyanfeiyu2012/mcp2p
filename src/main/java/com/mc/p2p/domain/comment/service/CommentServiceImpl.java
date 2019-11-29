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
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private CommentRepository commentRepository;

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
        System.out.println("文件处理结束");
        VoiceDo voiceDo = new VoiceDo(ffmpegDo.getTargetFile(),ffmpegDo.getFileId());
        voiceDo.setComment();
//        voiceDo.setScore();
        saveCmment(request,voiceDo,customer);
    }

    public void saveCmment(CommentReq req, VoiceDo voiceDo,Customer customer){
        Comment comment = new Comment();
        String context = "";
        for(String text : voiceDo.getSpeechData().getResult()){
            context += text;
        }
        comment.setVideoId(req.getVideoId());
        comment.setUid(req.getUid());
        comment.setUname(customer.getWxName());
        comment.setAvatar(customer.getAvatar());
        comment.setCommentId(voiceDo.getFileId());
        comment.setVoicePath(voiceDo.getVoicePath());
        comment.setContext(context);
        comment.setScore(voiceDo.getScore());
        comment.setSentiment(voiceDo.getNlpData().getSentiment());
        comment.setNegative(String.valueOf(voiceDo.getNlpData().getNegative()));
        comment.setPositive(String.valueOf(voiceDo.getNlpData().getPositive()));
        commentRepository.saveComment(comment);
    }

    @Override
    public List<CommentListQueryResp> getCommentList(String videoId) {
        List<Comment> commentList =  commentRepository.getCommentList(videoId);
        return commentList.stream()
                .map(comment -> CommentListQueryResp.builder()
                        .userId(comment.getUid())
                        .userName(comment.getUname())
                        .avatar(comment.getAvatar())
                        .context(comment.getContext())
                        .sentiment(SentimentEnum.findChineseType(comment.getSentiment()))
                        .score(String.valueOf(comment.getScore()))
                        .build())
                .collect(Collectors.toList());
    }
}
