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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static AipSpeech SPEECH_CLIENT;
    private static AipNlp AIP_CLIENT;

    private static Credential CRED;
    private static HttpProfile HTTP_PROFILE;
    private static ClientProfile CLIENT_PROFILE;
    private static NlpClient NLP_CLIENT;

    static {
        SPEECH_CLIENT = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        AIP_CLIENT = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        SPEECH_CLIENT.setConnectionTimeoutInMillis(2000);
        SPEECH_CLIENT.setSocketTimeoutInMillis(60000);


        // 可选：设置网络连接参数
        AIP_CLIENT.setConnectionTimeoutInMillis(2000);
        AIP_CLIENT.setSocketTimeoutInMillis(60000);


        CRED = new Credential("AKIDd9UgmhsxJXcaO2cmYFl6GE2e7HJAd4tX", "b1GJBXing8RZWHrRryynXCh19A1gAORJ");

        HTTP_PROFILE = new HttpProfile();
        HTTP_PROFILE.setEndpoint("nlp.tencentcloudapi.com");

        CLIENT_PROFILE = new ClientProfile();
        CLIENT_PROFILE.setHttpProfile(HTTP_PROFILE);

        NLP_CLIENT = new NlpClient(CRED, "ap-guangzhou", CLIENT_PROFILE);
    }

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

    public VoiceDo(String path, String fileId) {
        this.voicePath = path;
        this.fileId = fileId;
    }

    private static final Logger log = LoggerFactory.getLogger(VoiceDo.class);

    public void setComment() {
        log.info("voice recognize start");
        // 初始化一个AipSpeech

        HashMap<String, Object> options = new HashMap<>();
        options.put("rate", 16000);
        JSONObject speechRes = new JSONObject();
        // 调用接口
        try {
            byte[] data = Util.readFileByBytes(this.voicePath);
            System.out.println(data.length);
            speechRes = SPEECH_CLIENT.asr(reSamplingPCM(data), "wav", 16000, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            SpeechData speechData = SJacksonUtil.extractPojo(speechRes.toString(2), SpeechData.class);
            System.out.println(speechData);
            if (SuccessCode.equals(speechData.getErr_no())) {
                this.speechData = speechData;
                System.out.println(speechData);
                setScore();
            } else {
                log.info("voice can't recognized");
                throw new BusinessException(VOICE_COMMENT_CAN_NOT_RECOGNIZED);
            }
        }catch (Exception e){
            log.error("voice recognize error，exception is {}",e.getStackTrace());
            throw new BusinessException(VOICE_COMMENT_CAN_NOT_RECOGNIZED);
        }

        log.info("voice recognize finished");
    }

    private byte[] reSamplingPCM(byte[] data) {

        log.info("reSampling start");
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(new ByteArrayInputStream(data));
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

            log.info("reSampling finish");
            return outputStream.toByteArray();
        } catch (UnsupportedAudioFileException | IOException e) {
            log.error("occurs errors when re-sampling the audio stream:{}",e.getStackTrace());
            throw new BusinessException(VOICE_COMMENT_CAN_NOT_RECOGNIZED);
        }
    }

    public void setScore() {
        try {

            log.info("sentiment analyze start");


            String params = "{\"Text\":\"%s\"}";
            String texts = "";
            for (String text : this.speechData.result) {
                texts += text;
            }
            params = String.format(params,texts);

            System.out.println(params);
            SentimentAnalysisRequest req = SentimentAnalysisRequest.fromJsonString(params, SentimentAnalysisRequest.class);

            SentimentAnalysisResponse resp = NLP_CLIENT.SentimentAnalysis(req);

            System.out.println(resp);
            this.nlpData = resp;
            this.score = BigDecimal.valueOf(resp.getPositive()).movePointRight(1).intValue();
            log.info("sentiment analyze success");
        } catch (Exception e) {
            log.error("sentiment analyze error, exception is {}",e.getStackTrace());
            throw new BusinessException(VOICE_COMMENT_CAN_NOT_RECOGNIZED);
        }
    }
}
