package com.mc.p2p.domain.comment.entity;

import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.VideoUploadReq;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author : Yuan.Pan 2019/11/26 8:47 PM
 */
@Data
@Slf4j
public class CommentDo {

    /** 视频ID */
    private String videoId;

    /** 评论路径 */
    private String filePath;

    /** 文件ID */
    private String fileId;

    /** 用户ID */
    private String uid;

    /** 评论文件文件 */
    private MultipartFile file;

    public CommentDo(Customer customer, Video video, MultipartFile file) {
        if (null == customer) {
            throw new BusinessException(ResponseEnum.LOGIN_LIMIT);
        }
        if (null == video) {
            throw new BusinessException(ResponseEnum.COMMENT_VIDEO_NULL);
        }

        this.videoId = video.getVideoId();
        this.uid = customer.getOpenId();
        this.file = file;
    }

    public void storage() {
        String fileId = UUID.randomUUID().toString();
        String filePath = McConstant.FILE_VOICE_PATH + fileId + "." + StringUtils.getFilenameExtension(this.file.getOriginalFilename());
        File target = new File(filePath);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            log.error("comment file storage err e-{}", e);
            throw new BusinessException(ResponseEnum.FILE_STORAGE_ERR);
        }

        this.fileId = fileId;
        this.filePath = filePath;
    }
}
