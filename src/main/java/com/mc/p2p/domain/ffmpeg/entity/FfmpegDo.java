package com.mc.p2p.domain.ffmpeg.entity;

import com.google.common.collect.Lists;
import com.mc.p2p.infrastructure.constant.McConstant;
import com.mc.p2p.infrastructure.enums.FfmpegTypeEnum;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.mc.p2p.model.po.Bgm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;
import java.util.UUID;

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

    /** 时间 */
    private Double fileTime;

    /** 文件ID */
    private String fileId;

    public FfmpegDo(String sourceFile, String fileId) {
        this.sourceFile = sourceFile;
        this.fileId = fileId;
    }

    public FfmpegDo(String sourceFile, Bgm bgm, String fileId, Double fileTime) {
        this.fileId = fileId;
        this.fileTime = fileTime;
        this.sourceFile = sourceFile;

        if (null != bgm) {
            this.bondFile = McConstant.FILE_BGM_PATH + StringUtils.getFilename(bgm.getBgmUri());
        }
    }

    public void execute(FfmpegTypeEnum actionType) {
        // init target file
        targetFile(actionType);
        if (FfmpegTypeEnum.CONVERT_VOICE == actionType && this.sourceFile.endsWith(McConstant.WAV_EXT)) {
            return;
        }

        doExecute(this.command(actionType));

        if (FfmpegTypeEnum.SCREENSHOT != actionType) {
            boolean delete = new File(this.sourceFile).delete();
            this.sourceFile = this.targetFile;
        }
    }

    private void targetFile(FfmpegTypeEnum actionType) {
        String fullName = StringUtils.getFilename(this.sourceFile);
        String filename = fullName.substring(0, fullName.indexOf("."));

        String fileId;
        String targetFilePath;
        switch (actionType) {
            case MIX_BGM:
            case CANCEL_BGM:
            case CONVERT_VIDEO:
            case COMPRESS_VIDEO:
            case ADD_WATER:
                fileId = UUID.randomUUID().toString();
                targetFilePath = McConstant.FILE_VIDEO_PATH + fileId + McConstant.MP4_EXT;
                break;
            case CONVERT_VOICE:
                fileId = filename;
                targetFilePath = McConstant.FILE_VOICE_PATH + fileId + McConstant.WAV_EXT;
                break;
            case SCREENSHOT:
                fileId = filename;
                targetFilePath = McConstant.FILE_BG_PATH + fileId + McConstant.JPG_EXT;
                break;
                default:
                    throw new BusinessException(ResponseEnum.NOT_SUPPORT_ACTION);

        }

        this.fileId = fileId;
        this.targetFile = targetFilePath;
    }

    private List<String> command(FfmpegTypeEnum actionType) {
        String command;
        switch (actionType) {
            case MIX_BGM:
                command = String.format(actionType.getCommand(), this.sourceFile, this.bondFile, this.fileTime, this.targetFile);
                break;
            case CANCEL_BGM:
            case CONVERT_VIDEO:
            case CONVERT_VOICE:
            case SCREENSHOT:
            case COMPRESS_VIDEO:
            case ADD_WATER:
                command = String.format(actionType.getCommand(), this.sourceFile, this.targetFile);
                break;
                default:
                    throw new BusinessException(ResponseEnum.NOT_SUPPORT_ACTION);
        }

        return Lists.newArrayList(command.split(" "));
    }

    public static void doExecute(List<String> command) {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
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
            throw new BusinessException(ResponseEnum.FILE_UPLOAD_ERR);
        }
    }
}
