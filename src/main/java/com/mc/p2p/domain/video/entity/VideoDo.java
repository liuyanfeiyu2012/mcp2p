package com.mc.p2p.domain.video.entity;

import com.mc.p2p.domain.discern.entity.DiscernDo;
import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.enums.StatusEnum;
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
 * @auther: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Slf4j
@Data
public class VideoDo {

    /**
     * 文件存储path
     */
    private String videoPath;

    /**
     * 文件ID
     */
    private String videoId;

    /**
     * 视频基本信息
     */
    private VideoUploadReq videoInfo;

    /**
     * 视频文件
     */
    private MultipartFile file;

    /**
     * 构造方法
     *
     * @param videoInfo 视频信息
     * @param file      文件
     * @param customer  用户
     */
    public VideoDo(VideoUploadReq videoInfo, MultipartFile file, Customer customer) {
        if (null == customer) {
            throw new BusinessException(ResponseEnum.LOGIN_LIMIT);
        }
        if (videoInfo.getVideoTime() > McConstant.MAX_LIMIT) {
            throw new BusinessException(ResponseEnum.VIDEO_MAX_TIME_LIMIT);
        }

        if (videoInfo.getVideoTime() < McConstant.MIN_LIMIT) {
            throw new BusinessException(ResponseEnum.VIDEO_MIN_TIME_LIMIT);
        }

        this.videoInfo = videoInfo;
        this.file = file;
    }

    /**
     * 存储文件
     */
    public void storageFile() {
        String fileId = UUID.randomUUID().toString();
        String filePath = McConstant.FILE_VIDEO_PATH
                + fileId + "."
                + StringUtils.getFilenameExtension(this.file.getOriginalFilename());
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

    /**
     * 更新文件
     * @param fileId 文件编号
     * @param sourceFileId 源路径
     * @param discernDo 识别信息
     * @return 文件
     */
    public Video updateVideo(String fileId, String sourceFileId, DiscernDo discernDo) {
        Video record = new Video();
        record.setVideoId(sourceFileId);
        record.setStatus(StatusEnum.ENABLE.getCode());
        if (new File(McConstant.FILE_VIDEO_PATH).exists()) {
            record.setVideoUri(McConstant.VIDEO_NGINX_PREFFIX + fileId + McConstant.MP4_EXT);
        }

        if (null != discernDo) {
            record.setScore(discernDo.getScore());
            record.setAnimal(discernDo.getName());
            record.setMemo(discernDo.getDescription());
        }
        return record;
    }

    /**
     * 视频
     * @param customer 用户信息
     * @return 视频信息
     */
    public Video video(Customer customer) {
        Video record = new Video();
        record.setLikeCount(0);
        record.setVideoId(this.videoId);
        record.setVideoMemo(this.videoInfo.getVideoMemo());
        record.setVideoTime(this.videoInfo.getVideoTime());
        record.setUid(this.videoInfo.getUid());
        record.setAvatar(customer.getAvatar());
        record.setWxName(customer.getWxName());
        record.setCreateTime(new Date());
        record.setStatus(StatusEnum.DISABLE.getCode());

        record.setVideoUri(McConstant.VIDEO_NGINX_PREFFIX + this.videoId + McConstant.MP4_EXT);
        record.setVideoBgUri(McConstant.BG_NGINX_PREFFIX + this.videoId + McConstant.JPG_EXT);
        return record;
    }
}
