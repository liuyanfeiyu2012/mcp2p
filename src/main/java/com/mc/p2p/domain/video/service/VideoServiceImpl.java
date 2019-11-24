package com.mc.p2p.domain.video.service;

import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.domain.ffmpeg.service.FfmpegService;
import com.mc.p2p.domain.video.entity.VideoDo;
import com.mc.p2p.domain.video.repository.VideoRepository;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.mc.p2p.model.vo.VideoUploadReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Yuan.Pan 2019/11/23 9:08 AM
 */
@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private FfmpegService ffmpegService;

    @Transactional
    @Override
    public void saveVideo(VideoUploadReq request, MultipartFile file) {
        VideoDo videoDo = new VideoDo(request, file);

        try {
            // 存储文件
            videoDo.storageFile();

            // 文件流加工
            ffmpegService.videoFilter(new FfmpegDo(videoDo.getFileName()));

            // 媒体文件持久化
            videoRepository.saveVideo(videoDo.video());
        } catch (Exception e) {
            log.error("文件上传失败: REQ-{}, e-{}", request, e);
            throw new BusinessException(ResponseEnum.FILE_UPLOAD_ERR);
        }
    }
}
