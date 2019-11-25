package com.mc.p2p.domain.ffmpeg.service;

import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.domain.ffmpeg.entity.VideoFfmDo;
import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.FfmpegTypeEnum;
import org.apache.commons.chain.Context;
import org.springframework.stereotype.Component;

/**
 * @author : Yuan.Pan 2019/11/23 10:40 AM
 */
@Component
public class Convert implements ConvertAble {

    @Override
    public boolean execute(Context context) throws Exception {
        FfmpegDo request = (FfmpegDo) context.get(McConstant.FFMPEG_DO_KEY);
        FfmpegTypeEnum actionType = (FfmpegTypeEnum) context.get(McConstant.CONVERT_KEY);

        // 流媒体处理器
        request.execute(actionType);

        // 重置操作文件
        request.setSourceFile(request.getTargetFile());

        // 设置参数
        if (null != request.getBizDo() && request.getBizDo() instanceof VideoFfmDo) {
            VideoFfmDo bizDo = (VideoFfmDo) request.getBizDo();
            bizDo.setVideoPath(request.getTargetFile());
        }

        return false;
    }
}