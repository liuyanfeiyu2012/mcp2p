package com.mc.p2p.domain.video.service;

import com.google.common.collect.Lists;
import com.mc.p2p.domain.customer.service.CustomerService;
import com.mc.p2p.domain.discern.entity.DiscernDo;
import com.mc.p2p.domain.discern.service.DiscernService;
import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.domain.ffmpeg.service.FfmpegService;
import com.mc.p2p.domain.video.entity.VideoDo;
import com.mc.p2p.domain.video.repository.BgmRepository;
import com.mc.p2p.domain.video.repository.VideoRepository;
import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.mc.p2p.model.po.Bgm;
import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.BgmQueryResp;
import com.mc.p2p.model.vo.VideoQueryResp;
import com.mc.p2p.model.vo.VideoUploadReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DiscernService discernService;

    @Override
    public void saveVideo(VideoUploadReq request, MultipartFile file) {
        log.info("[用户上传视频 REQ-{}]", request);

        Customer customer = customerService.selectByOpenId(request.getUid());
        VideoDo videoDo = new VideoDo(request, file, customer);

        try {
            // 存储文件
            videoDo.storageFile();

            // 媒体文件加工
            Bgm bgm = bgmRepository.selectBgm(request.getBgmId());
            FfmpegDo ffmpegDo = new FfmpegDo(videoDo.getVideoPath(), bgm, videoDo.getVideoId(), request.getVideoTime());
            ffmpegService.videoFilter(ffmpegDo);

            // 媒体文件持久化
            Video video = videoDo.video(ffmpegDo.getFileId(), customer);
            videoRepository.saveVideo(video);

            new Thread(() -> {
                try {
                    DiscernDo discern = discernService.discern(McConstant.VIDEO_NGINX_PREFFIX + ffmpegDo.getFileId() + ".mp4");
                    videoRepository.saveDiscern(discern, video.getVideoId());
                } catch (Exception e) {
                    log.error("ai animal err e-{}", e);
                }
            }).start();
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
        List<Video> videoList = videoRepository.selectList(null);
        return assembler(videoList);
    }

    @Override
    public List<VideoQueryResp> selectVideoList(String openId) {
        List<Video> videoList = videoRepository.selectList(openId)
                .stream()
                .sorted(Comparator.comparing(Video::getCreateTime).reversed())
                .collect(Collectors.toList());

        return assembler(videoList);
    }

    @Override
    public Integer likeCount(String openId) {
        return videoRepository.likeCount(openId);
    }

    @Override
    public Video selectOne(String videoId) {
        return videoRepository.selectOne(videoId);
    }

    private List<VideoQueryResp> assembler(List<Video> videoList) {
        if (CollectionUtils.isEmpty(videoList)) {
            return Lists.newArrayList();
        }

        List<VideoQueryResp> resultList = Lists.newArrayList();
        videoList.forEach(item -> {
            VideoQueryResp record = new VideoQueryResp();
            BeanUtils.copyProperties(item, record);
            if (StringUtils.isEmpty(item.getAnimal())) {
                resultList.add(record);
                return;
            }

            List<String> productList = getProductList(item.getAnimal());
            int random = (int)(Math.random() * (productList.size() - 1 ) + 1);
            record.setAnimal(item.getAnimal());
            record.setDescription(item.getMemo());
            record.setRecommendProduct(productList.get(random));
            resultList.add(record);
        });

        return resultList;
    }

    private List<String> getProductList(String animal) {
        if (animal.contains("猫")) {
            return McConstant.CAT_PRODUCT;
        }
        if (animal.contains("狗") || animal.contains("柯基") || animal.contains("哈士奇")
                || animal.contains("阿拉斯加") || animal.contains("犬") || animal.contains("萨摩耶")
                || animal.contains("拉布拉多") || animal.contains("吉娃娃") || animal.contains("泰迪")
                || animal.contains("梗") || animal.contains("獒")) {
            return McConstant.DOG_PRODUCT;
        }
        if (animal.contains("鱼")) {
            return McConstant.FISH_PRODUCT;
        }
        return McConstant.NOMAL_PRODUCT;
    }
}
