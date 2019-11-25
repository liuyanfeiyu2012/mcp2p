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
import org.springframework.util.StringUtils;

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
    private CancelBgmAble cancelBgmAble;

    @Autowired
    private ScreenshotAble screenshotAble;

    @Override
    @SuppressWarnings("all")
    public void videoFilter(FfmpegDo request) throws Exception {
        Context context = new ContextBase();
        context.put(McConstant.FFMPEG_DO_KEY, request);
        context.put(McConstant.CONVERT_KEY, FfmpegTypeEnum.CONVERT_VIDEO);
        context.put(McConstant.MIX_BGM_KEY, FfmpegTypeEnum.MIX_BGM);
        context.put(McConstant.CANCEL_BGM_KEY, FfmpegTypeEnum.CANCEL_BGM);
        context.put(McConstant.SCREENSHOT_KEY, FfmpegTypeEnum.SCREENSHOT);

        Chain executorChain = new ChainBase();
        if (!"mp4".equals(StringUtils.getFilenameExtension(request.getSourceFile()))) {
            executorChain.addCommand(convertAble);
        }

        if (!StringUtils.isEmpty(request.getBondFile())) {
            executorChain.addCommand(cancelBgmAble);
            executorChain.addCommand(mixBgmAble);
        }
        executorChain.addCommand(screenshotAble);

        executorChain.execute(context);
    }
}
