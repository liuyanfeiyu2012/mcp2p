package com.mc.p2p.domain.video.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mc.p2p.domain.customer.service.CustomerService;
import com.mc.p2p.domain.discern.entity.DiscernDo;
import com.mc.p2p.domain.discern.service.DiscernService;
import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.domain.ffmpeg.service.FfmpegService;
import com.mc.p2p.domain.video.entity.VideoDo;
import com.mc.p2p.domain.video.repository.BgmRepository;
import com.mc.p2p.domain.video.repository.VideoRepository;
import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.mapper.VideoMapper;
import com.mc.p2p.model.po.Bgm;
import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.po.Video;
import com.mc.p2p.model.vo.BgmQueryResp;
import com.mc.p2p.model.vo.CityCircleResp;
import com.mc.p2p.model.vo.VideoQueryResp;
import com.mc.p2p.model.vo.VideoUploadReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoRepository videoRepository;

    @Resource
    private FfmpegService ffmpegService;

    @Resource
    private BgmRepository bgmRepository;

    @Resource
    private CustomerService customerService;

    @Resource
    private DiscernService discernService;

    @Resource
    private VideoMapper videoMapper;

    @Override
    public void saveVideo(VideoUploadReq request, MultipartFile file) {
        log.info("[用户上传视频 REQ-{}]", request);

        Customer customer = customerService.selectByOpenId(request.getUid());
        VideoDo videoDo = new VideoDo(request, file, customer);

        // 存储文件
        videoDo.storageFile();
        Video sourceVideo = videoDo.video(customer);
        FfmpegDo picHandleDo = new FfmpegDo(videoDo.getVideoPath(), null,
                videoDo.getVideoId(), request.getVideoTime());
        ffmpegService.videoPicFilter(picHandleDo);
        videoRepository.saveVideo(sourceVideo);

        // 处理流文件
        handle(videoDo, request, sourceVideo.getVideoId());
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

    @Override
    public List<CityCircleResp> selectCityCircle() {
        return videoMapper.circle();
    }

    private static final int COMMENT_SIZE = 2;

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

            record.setAnimal(item.getAnimal());
            record.setDescription(item.getMemo());

            List<String> productList = getProductList(item.getAnimal());

            try {
                Set<String> commendSet = Sets.newHashSet();
                while (COMMENT_SIZE != commendSet.size()) {
                    SecureRandom secureRandom = new SecureRandom(new byte[20]);
                    int random = (int) (secureRandom.nextFloat() * (productList.size() - 1) + 1);
                    commendSet.add(productList.get(random));
                }

                record.setRecommendProduct(commendSet);
                resultList.add(record);
            } catch (Exception e) {
                log.error("no such algorithm");
            }
        });

        return resultList;
    }

    private List<String> getProductList(String animal) {
        if (animal.contains(McConstant.CATE_A)) {
            return McConstant.CAT_PRODUCT;
        }
        if (animal.contains(McConstant.DOG_J) || animal.contains(McConstant.DOG_K)
                || animal.contains(McConstant.DOG_A) || animal.contains(McConstant.DOG_B)
                || animal.contains(McConstant.DOG_C) || animal.contains(McConstant.DOG_D)
                || animal.contains(McConstant.DOG_E) || animal.contains(McConstant.DOG_F)
                || animal.contains(McConstant.DOG_G) || animal.contains(McConstant.DOG_H)
                || animal.contains(McConstant.DOG_I)) {
            return McConstant.DOG_PRODUCT;
        }
        if (animal.contains(McConstant.FISH_A)) {
            return McConstant.FISH_PRODUCT;
        }
        return McConstant.NOMAL_PRODUCT;
    }

    private void handle(VideoDo videoDo, VideoUploadReq request, String sourceId) {
        new Thread(() -> {
            CompletableFuture<FfmpegDo> ffmpegFileFuture = CompletableFuture.supplyAsync(() -> {
                FfmpegDo ffmpegDo = new FfmpegDo(
                        videoDo.getVideoPath(),
                        bgmRepository.selectBgm(request.getBgmId()),
                        videoDo.getVideoId(),
                        request.getVideoTime());

                ffmpegService.videoFilter(ffmpegDo);
                return ffmpegDo;
            });

            CompletableFuture<DiscernDo> discernFuture = CompletableFuture.supplyAsync(() ->
                    discernService.discern(McConstant.FILE_VIDEO_PATH
                            + videoDo.getVideoId() + McConstant.MP4_EXT));

            CompletableFuture.allOf(ffmpegFileFuture, discernFuture).thenRun(() -> {
                try {
                    FfmpegDo ffmpegDo = ffmpegFileFuture.get();
                    DiscernDo discernDo = discernFuture.get();

                    Video video = videoDo.updateVideo(ffmpegDo.getFileId(), sourceId, discernDo);
                    videoRepository.updateVideo(video);
                } catch (Exception e) {
                    log.error("处理文件异常 e-{}", e);
                }
            });
        }).start();
    }
}
