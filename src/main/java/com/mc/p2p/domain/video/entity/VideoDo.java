package com.mc.p2p.domain.video.entity;

import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.VideoUploadReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author : Yuan.Pan 2019/11/23 9:21 AM
 */
@Slf4j
@Data
public class VideoDo {

    /** 文件fileName */
    private String fileName;

    /** 文件ID */
    private String fileId;

    /** 文件 */
    private String fileBgUri;

    /** 视频基本信息 */
    private VideoUploadReq videoInfo;

    /** 视频文件 */
    private MultipartFile file;

    public VideoDo(VideoUploadReq videoInfo, MultipartFile file) {
        this.videoInfo = videoInfo;
        this.file = file;
    }

    public void storageFile() {
        String fileId = UUID.randomUUID().toString();
        String fileName = McConstant.FILE_VIDEO_PATH + fileId + StringUtils.getFilenameExtension(this.file.getOriginalFilename());
        File target = new File(fileName);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            log.error("video file storage err e-{}", e);
            throw new BusinessException(ResponseEnum.FILE_STORAGE_ERR);
        }

        this.fileId = fileId;
        this.fileName = fileName;
    }

    public Video video() {
        Video record = new Video();
        record.setLikeCount(0);
        record.setUid(this.videoInfo.getUid());
        record.setVideoId(this.fileId);
        record.setVideoBgUri(this.fileBgUri);
        record.setVideoUri(this.getFileName());
        record.setVideoMemo(this.videoInfo.getVideoMemo());
        record.setVideoTime(this.videoInfo.getVideoTime());
        return record;
    }
}
