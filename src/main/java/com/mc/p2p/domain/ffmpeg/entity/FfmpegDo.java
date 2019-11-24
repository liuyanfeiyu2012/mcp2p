package com.mc.p2p.domain.ffmpeg.entity;

import com.google.common.collect.Lists;
import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.FfmpegTypeEnum;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.exception.BusinessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/23 10:28 AM
 */
@Slf4j
@Data
public class FfmpegDo {

    /** 源文件路径 */
    private String sourceFile;

    /** 操作完后的目标文件路径 */
    private String targetFile;

    /** 支持和主文件绑定一个附属文件, 比如视频的bgm */
    private String bondFile;

    public FfmpegDo(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public FfmpegDo(String sourceFile, String bondFile) {
        this.sourceFile = sourceFile;
        this.bondFile = bondFile;
    }

    public void execute(FfmpegTypeEnum actionType) {
        // init target file
        targetFile(actionType);

        ProcessBuilder processBuilder = new ProcessBuilder(this.command(actionType));
        try {
            Process process = processBuilder.start();
            InputStream stream = process.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line = null;
            while ((line = br.readLine()) != null){}
            br.close();
            stream.close();
        } catch (IOException e) {
            log.error("[file execute err e-{}]", e);
        }
    }

    private void targetFile(FfmpegTypeEnum actionType) {
        String fullName = StringUtils.getFilename(this.sourceFile);
        String filename = fullName.substring(0, fullName.indexOf("."));

        String finalFileName;
        switch (actionType) {
            case MIX_BGM:
            case CONVERT_VIDEO:
                finalFileName = McConstant.FILE_VIDEO_PATH + filename + ".mp4";
                break;
            case CONVERT_VOICE:
                finalFileName = McConstant.FILE_VOICE_PATH + filename + ".wav";
                break;
            case SCREENSHOT:
                finalFileName = McConstant.FILE_BG_PATH + filename + ".jpg";
                break;
                default:
                    throw new BusinessException(ResponseEnum.NOT_SUPPORT_ACTION);

        }

        this.targetFile = finalFileName;
    }

    private List<String> command(FfmpegTypeEnum actionType) {
        String command;
        switch (actionType) {
            case MIX_BGM:
                command = String.format(actionType.getCommand(), this.sourceFile, this.bondFile, this.targetFile);
                break;
            case CONVERT_VIDEO:
            case CONVERT_VOICE:
            case SCREENSHOT:
                command = String.format(actionType.getCommand(), this.sourceFile, this.targetFile);
                break;
                default:
                    throw new BusinessException(ResponseEnum.NOT_SUPPORT_ACTION);
        }

        return Lists.newArrayList(command.split(" "));
    }

    public static void main(String[] args) {
        String filename = StringUtils.getFilename("/11/22/1asdasda.txt");
        String substring = filename.substring(0, filename.indexOf("."));
        System.out.println(substring);
    }
}
