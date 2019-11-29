package com.mc.p2p.domain.ffmpeg.service;

import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.FfmpegTypeEnum;
import org.apache.commons.chain.Context;
import org.springframework.stereotype.Service;

/**
 * @author : Yuan.Pan 2019/11/25 11:12 PM
 */
@Service
public class CancelBgm implements CancelBgmAble {

    /**
     * 执行
     *
     * @param context 内容
     * @return 执行结果
     * @throws Exception 抛出异常
     */
    @Override
    public boolean execute(Context context) throws Exception {
        FfmpegDo request = (FfmpegDo) context.get(McConstant.FFMPEG_DO_KEY);
        FfmpegTypeEnum actionType = (FfmpegTypeEnum) context.get(McConstant.CANCEL_BGM_KEY);

        // 流媒体处理器
        request.execute(actionType);
        return false;
    }
}
