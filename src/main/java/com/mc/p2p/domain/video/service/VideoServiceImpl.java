package com.mc.p2p.domain.video.service;

import com.google.common.collect.Lists;
import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.domain.ffmpeg.service.FfmpegService;
import com.mc.p2p.domain.video.entity.VideoDo;
import com.mc.p2p.domain.video.repository.BgmRepository;
import com.mc.p2p.domain.video.repository.VideoRepository;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.mc.p2p.model.po.Bgm;
import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.BgmQueryResp;
import com.mc.p2p.model.vo.VideoQueryResp;
import com.mc.p2p.model.vo.VideoUploadReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @Autowired
    private BgmRepository bgmRepository;

    @Transactional
    @Override
    public void saveVideo(VideoUploadReq request, MultipartFile file) {
        VideoDo videoDo = new VideoDo(request, file);

        try {
            // 存储文件
            videoDo.storageFile();

            // 媒体文件加工
            FfmpegDo ffmpegDo = new FfmpegDo(videoDo.getVideoPath(), bgmRepository.selectBgmPath(request.getBgmId()), videoDo.getVideoId());
            ffmpegService.videoFilter(ffmpegDo);

            // 媒体文件持久化
            videoRepository.saveVideo(videoDo.video(ffmpegDo.getFileId(), ffmpegDo.getTargetFile()));
        } catch (Exception e) {
            log.error("文件上传失败: REQ-{}, e-{}", request, e);
            throw new BusinessException(ResponseEnum.FILE_UPLOAD_ERR);
        }
    }

    @Override
    public List<BgmQueryResp> selectBgmList() {
        List<Bgm> bgmList = bgmRepository.selectList();

        List<BgmQueryResp> resultList = Lists.newArrayList();
        bgmList.forEach(item -> {
            BgmQueryResp record = new BgmQueryResp();
            BeanUtils.copyProperties(item, record);
            resultList.add(record);
        });

        return resultList;
    }

    @Override
    public List<VideoQueryResp> selectVideoList() {
        List<Video> videoList = videoRepository.selectList();

        List<VideoQueryResp> resultList = Lists.newArrayList();
        videoList.forEach(item -> {
            VideoQueryResp record = new VideoQueryResp();
            BeanUtils.copyProperties(item, record);
            resultList.add(record);
        });

        return resultList;
    }
}
