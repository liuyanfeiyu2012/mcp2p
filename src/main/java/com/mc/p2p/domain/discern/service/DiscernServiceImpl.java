package com.mc.p2p.domain.discern.service;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.google.common.collect.Lists;
import com.mc.p2p.domain.discern.entity.DiscernDo;
import com.mc.p2p.domain.discern.entity.DiscernItem;
import com.mc.p2p.domain.discern.entity.DiscernResponse;
import com.mc.p2p.domain.ffmpeg.entity.FfmpegDo;
import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.FfmpegTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Slf4j
@Service
public class DiscernServiceImpl implements DiscernService {

    /**
     * 识图
     *
     * @param filePath 文件路径
     * @return 识图信息
     */
    @Override
    public DiscernDo discern(String filePath) {
        AipImageClassify client = new AipImageClassify(McConstant.AI_APP_ID,
                McConstant.AI_API_KEY, McConstant.AI_SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 截取三张图
        List<String> filePathList = Lists.newArrayList();
        for (int i = 0; i < McConstant.AI_SC_NUM; i++) {
            String picPath = McConstant.FILE_DISCERN_PATH
                    + UUID.randomUUID().toString()
                    + McConstant.JPG_EXT;
            String command = FfmpegTypeEnum.AI_SCREENSHOT.getCommand();
            String finalCommand = String.format(command, "00:00:0" + i, filePath, picPath);
            FfmpegDo.doExecute(Lists.newArrayList(finalCommand.split(" ")));
            filePathList.add(picPath);
        }


        HashMap<String, String> options = new HashMap<>(16);
        options.put("top_num", "3");
        options.put("baike_num", "5");

        DiscernDo discernDo = null;
        for (String picPath : filePathList) {
            try {
                JSONObject response = client.animalDetect(picPath, options);
                String result = response.toString(2);
                DiscernResponse responseObj =
                        com.alibaba.fastjson.JSONObject.parseObject(result, DiscernResponse.class);
                List<DiscernItem> discernItemList = responseObj.getResult();
                DiscernItem discernItem = discernItemList.get(0);
                if (McConstant.NO_ANIMAL.equals(discernItem.getName())) {
                    continue;
                }

                log.info("AI RESP -{}", result);
                discernDo = new DiscernDo(discernItem.getName(),
                        discernItem.getScore(),
                        discernItem.getBaike_info()
                                .getDescription());
            } catch (Exception e) {
                log.error("get animal err e-{}", e);
            }
        }

        return discernDo;
    }
}
