package com.mc.p2p.domain.video.entity;

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
import java.util.Date;
import java.util.UUID;

/**
 * @author : Yuan.Pan 2019/11/23 9:21 AM
 */
@Slf4j
@Data
public class VideoDo {

    /** 文件存储path */
    private String videoPath;

    /** 文件ID */
    private String videoId;

    /** 视频基本信息 */
    private VideoUploadReq videoInfo;

    /** 视频文件 */
    private MultipartFile file;

    public VideoDo(VideoUploadReq videoInfo, MultipartFile file, Customer customer) {
        if (null == customer) {
            throw new BusinessException(ResponseEnum.LOGIN_LIMIT);
        }
        if (videoInfo.getVideoTime() > 15) {
            throw new BusinessException(ResponseEnum.VIDEO_MAX_TIME_LIMIT);
        }

        this.videoInfo = videoInfo;
        this.file = file;
    }

    public void storageFile() {
        String fileId = UUID.randomUUID().toString();
        String filePath = McConstant.FILE_VIDEO_PATH + fileId + "." + StringUtils.getFilenameExtension(this.file.getOriginalFilename());
        File target = new File(filePath);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            log.error("video file storage err e-{}", e);
            throw new BusinessException(ResponseEnum.FILE_STORAGE_ERR);
        }

        this.videoId = fileId;
        this.videoPath = filePath;
    }

    public Video video(String fileId) {
        Video record = new Video();
        record.setLikeCount(0);
        record.setVideoId(fileId);
        record.setUid(this.videoInfo.getUid());
        record.setVideoUri(McConstant.VIDEO_NGINX_PREFFIX + fileId + ".mp4");
        record.setVideoBgUri(McConstant.BG_NGINX_PREFFIX + fileId + ".jpg");
        record.setVideoMemo(this.videoInfo.getVideoMemo());
        record.setVideoTime(this.videoInfo.getVideoTime());
        record.setCreateTime(new Date());
        return record;
    }
}
