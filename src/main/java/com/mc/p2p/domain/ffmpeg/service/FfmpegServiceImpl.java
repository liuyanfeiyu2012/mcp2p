package com.mc.p2p.domain.ffmpeg.service;

import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.FfmpegTypeEnum;
import org.apache.commons.chain.Chain;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Yuan.Pan 2019/11/24 5:15 PM
 */
@Service
public class FfmpegServiceImpl implements FfmpegService {

    @Autowired
    private ConvertAble convertAble;

    @Autowired
    private MixBgmAble mixBgmAble;

    @Autowired
    private ScreenshotAble screenshotAble;

    @Override
    @SuppressWarnings("all")
    public void videoFilter(FfmpegDo request) throws Exception {
        Chain executorChain = new ChainBase();
        executorChain.addCommand(convertAble);
        executorChain.addCommand(mixBgmAble);
        executorChain.addCommand(screenshotAble);

        Context context = new ContextBase();
        context.put(McConstant.FFMPEG_DO_KEY, request);
        context.put(McConstant.CONVERT_KEY, FfmpegTypeEnum.CONVERT_VIDEO);
        context.put(McConstant.MIX_BGM_KEY, FfmpegTypeEnum.MIX_BGM);
        context.put(McConstant.SCREENSHOT_KEY, FfmpegTypeEnum.SCREENSHOT);

        executorChain.execute(context);
    }
}
