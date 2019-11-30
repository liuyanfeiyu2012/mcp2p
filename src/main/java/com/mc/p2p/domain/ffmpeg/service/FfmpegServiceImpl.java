package com.mc.p2p.domain.ffmpeg.service;

import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.FfmpegTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Chain;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @auther: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Slf4j
@Service
public class FfmpegServiceImpl implements FfmpegService {

    /**
     * 变量1
     */
    @Resource
    private ConvertAble convertAble;

    /**
     * 变量2
     */
    @Resource
    private MixBgmAble mixBgmAble;

    /**
     * 变量3
     */
    @Resource
    private CancelBgmAble cancelBgmAble;

    /**
     * bianliang 4
     */
    @Resource
    private ScreenshotAble screenshotAble;

    /**
     * 变量5
     */
    @Resource
    private CompressAble compressAble;

    /**
     * 变量6
     */
    @Resource
    private WaterAble waterAble;

    /**
     * 过滤
     *
     * @param request {param-0: video domain obj}
     */
    @Override
    @SuppressWarnings("all")
    public void videoFilter(FfmpegDo request) {
        Context context = new ContextBase();
        context.put(McConstant.FFMPEG_DO_KEY, request);
        context.put(McConstant.CONVERT_KEY, FfmpegTypeEnum.CONVERT_VIDEO);
        context.put(McConstant.COMPRESS_KEY, FfmpegTypeEnum.COMPRESS_VIDEO);
        context.put(McConstant.MIX_BGM_KEY, FfmpegTypeEnum.MIX_BGM);
        context.put(McConstant.CANCEL_BGM_KEY, FfmpegTypeEnum.CANCEL_BGM);
        context.put(McConstant.ADD_WATER_KEY, FfmpegTypeEnum.ADD_WATER);

        Chain executorChain = new ChainBase();
        // 转换格式
        if (!"mp4".equals(StringUtils.getFilenameExtension(
                request.getSourceFile()))) {
            executorChain.addCommand(convertAble);
        }

        // 压缩
        executorChain.addCommand(compressAble);

        // 去除背景音乐 && 混合音乐
        if (!StringUtils.isEmpty(request.getBondFile())) {
            executorChain.addCommand(cancelBgmAble);
            executorChain.addCommand(mixBgmAble);
        }

        // 添加水印
        executorChain.addCommand(waterAble);

        try {
            executorChain.execute(context);
        } catch (Exception e) {
            log.error("videoFilter err e-{}", e);
        }
    }

    /**
     * 过滤
     * @param request {param-0: request}
     */
    @Override
    @SuppressWarnings("all")
    public void videoPicFilter(FfmpegDo request) {
        Context context = new ContextBase();
        context.put(McConstant.FFMPEG_DO_KEY, request);
        context.put(McConstant.SCREENSHOT_KEY, FfmpegTypeEnum.SCREENSHOT);

        Chain executorChain = new ChainBase();
        executorChain.addCommand(screenshotAble);

        try {
            executorChain.execute(context);
        } catch (Exception e) {
            log.error("videoPicFilter err e-{}", e);
        }
    }

    /**
     * 评论过滤
     * @param request {param-0: comment}
     */
    @Override
    @SuppressWarnings("all")
    public void commentFilter(FfmpegDo request) {
        Context context = new ContextBase();
        context.put(McConstant.FFMPEG_DO_KEY, request);
        context.put(McConstant.CONVERT_KEY, FfmpegTypeEnum.CONVERT_VOICE);

        Chain executorChain = new ChainBase();
        executorChain.addCommand(convertAble);

        try {
            executorChain.execute(context);
        } catch (Exception e) {
            log.error("commentFilter err e-{}", e);
        }
    }
}
