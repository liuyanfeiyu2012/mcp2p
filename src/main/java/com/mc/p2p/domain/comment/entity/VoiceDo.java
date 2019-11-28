package com.mc.p2p.domain.comment.entity;

import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.util.Util;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.mc.p2p.utils.SJacksonUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.nlp.v20190408.NlpClient;
import com.tencentcloudapi.nlp.v20190408.models.SentimentAnalysisRequest;
import com.tencentcloudapi.nlp.v20190408.models.SentimentAnalysisResponse;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static com.mc.p2p.infrastructure.constant.McConstant.*;
import static com.mc.p2p.infrastructure.enums.ResponseEnum.VOICE_COMMENT_CAN_NOT_RECOGNIZED;

@Data
public class VoiceDo {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpeechData {
        Integer err_no;
        String err_msg;
        String corpus_no;
        String sn;
        List<String> result;
    }

    private SpeechData speechData;
    private SentimentAnalysisResponse nlpData;
    private String voicePath;
    private String fileId;
    private Integer score;

    public VoiceDo(String path,String fileId){
        this.voicePath = path;
        this.fileId = fileId;
    }

    public void setComment() {
        System.out.println("语音识别开始");
        // 初始化一个AipSpeech
        AipSpeech speechClient = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        AipNlp nlpClient = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        speechClient.setConnectionTimeoutInMillis(2000);
        speechClient.setSocketTimeoutInMillis(60000);


        // 可选：设置网络连接参数
        nlpClient.setConnectionTimeoutInMillis(2000);
        nlpClient.setSocketTimeoutInMillis(60000);
        HashMap<String, Object> options = new HashMap<>();
        options.put("rate", 16000);
        JSONObject speechRes = new JSONObject();
        // 调用接口
        try {
            byte[] data = Util.readFileByBytes(this.voicePath);
            System.out.println(data.length);
            speechRes = speechClient.asr(reSamplingPCM(data), "wav", 16000, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpeechData speechData = SJacksonUtil.extractPojo(speechRes.toString(2), SpeechData.class);
        System.out.println(speechData);
        if (SuccessCode.equals(speechData.getErr_no())) {
            this.speechData = speechData;
            System.out.println(speechData);
            setScore();
        } else {
            throw new BusinessException(VOICE_COMMENT_CAN_NOT_RECOGNIZED);
        }

        System.out.println("语音识别结束");
    }

    private byte[] reSamplingPCM(byte[] data) {

        System.out.println("转码开始");
        try(AudioInputStream audioIn = AudioSystem.getAudioInputStream(new ByteArrayInputStream(data));
            AudioInputStream convertedStream = AudioSystem.getAudioInputStream(DSTFORMAT, audioIn);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            if (audioIn.getFormat().matches(DSTFORMAT)) {
                return data;
            }

            int numReads = -1;

            int BUFF_SIZE = SAMPLE_RATE / 2;

            byte[] buff = new byte[BUFF_SIZE];

            while ((numReads = convertedStream.read(buff)) != -1) {
//                log.info("read {} byte(s)", numReads);
                outputStream.write(buff);
            }

            System.out.println("转码结束");
            return outputStream.toByteArray();
        } catch (UnsupportedAudioFileException | IOException e) {
//            log.error("occurs errors when re-sampling the audio stream:{}",e);
            throw new RuntimeException("occurs errors when re-sampling the audio stream:{}",e);
        }
    }

    public void setScore(){
        try{

            System.out.println("情感分析开始");
            Credential cred = new Credential("AKIDd9UgmhsxJXcaO2cmYFl6GE2e7HJAd4tX", "b1GJBXing8RZWHrRryynXCh19A1gAORJ");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("nlp.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            NlpClient client = new NlpClient(cred, "ap-guangzhou", clientProfile);

            String params = "";
            for(String text : this.speechData.result){
                params += text;
            }

            System.out.println(params);
            SentimentAnalysisRequest req = SentimentAnalysisRequest.fromJsonString(params, SentimentAnalysisRequest.class);

            SentimentAnalysisResponse resp = client.SentimentAnalysis(req);

            System.out.println(resp);
            this.nlpData = resp;
            this.score = BigDecimal.valueOf(resp.getPositive()).movePointRight(1).intValue();
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

        System.out.println("情感分析结束");
    }
}
