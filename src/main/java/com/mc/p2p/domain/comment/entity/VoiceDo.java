package com.mc.p2p.domain.comment.entity;

import com.baidu.aip.util.Util;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionRequest;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.nlp.v20190408.NlpClient;
import com.tencentcloudapi.nlp.v20190408.models.SentimentAnalysisRequest;
import com.tencentcloudapi.nlp.v20190408.models.SentimentAnalysisResponse;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import static com.mc.p2p.infrastructure.constant.McConstant.AUDIO_FORMAT;
import static com.mc.p2p.infrastructure.constant.McConstant.SAMPLE_RATE;
import static com.mc.p2p.infrastructure.enums.ResponseEnum.VOICE_COMMENT_CAN_NOT_RECOGNIZED;

/**
 * @auther: 谢星星
 * @Date: 2019/11/26 20:35
 * @Description:
 */
@Data
public class VoiceDo {

    /**
     * 情感分析验签
     */
    private static Credential CRED;

    /**
     * 网络头参数
     */
    private static HttpProfile HTTP_PROFILE;

    /**
     * 情感分析客户端参数
     */
    private static ClientProfile CLIENT_PROFILE;

    /**
     * 情感分析客户端
     */
    private static NlpClient NLP_CLIENT;

    /**
     * 语音识别验签
     */
    private static Credential CRED2;

    /**
     * 网络头参数
     */
    private static HttpProfile HTTP_PROFILE2;

    /**
     * 语音识别客户端参数
     */
    private static ClientProfile CLIENT_PROFILE2;

    /**
     * 语音识别客户端
     */
    private static AsrClient ASR_CLIENT;

    /**
     * 参数初始化
     */
    static {

        CRED = new Credential("AKIDd9UgmhsxJXcaO2cmYFl6GE2e7HJAd4tX",
                "b1GJBXing8RZWHrRryynXCh19A1gAORJ");
        CRED2 = new Credential("AKIDsDUVHo5M9k5F9N74X2JJ4ZonVxJP4pkP",
                "YqsjKelocAXgbGgsyRedOuAA3pNeniO9");

        HTTP_PROFILE = new HttpProfile();
        HTTP_PROFILE.setEndpoint("nlp.tencentcloudapi.com");
        CLIENT_PROFILE = new ClientProfile();
        CLIENT_PROFILE.setHttpProfile(HTTP_PROFILE);
        NLP_CLIENT = new NlpClient(CRED, "ap-guangzhou", CLIENT_PROFILE);

        HTTP_PROFILE2 = new HttpProfile();
        HTTP_PROFILE2.setEndpoint("asr.tencentcloudapi.com");
        CLIENT_PROFILE2 = new ClientProfile();
        CLIENT_PROFILE2.setHttpProfile(HTTP_PROFILE2);
        ASR_CLIENT = new AsrClient(CRED2, "ap-shanghai", CLIENT_PROFILE2);
    }

    /**
     * 语音识别数据
     */
    private SentenceRecognitionResponse speechData;

    /**
     * 情感分析数据
     */
    private SentimentAnalysisResponse nlpData;

    /**
     * 语音文件路径
     */
    private String voicePath;

    /**
     * 语音文件编号
     */
    private String fileId;

    /**
     * 情感分析得分
     */
    private Integer score;

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(VoiceDo.class);

    /**
     * 构造方法
     *
     * @param path   语音路径
     * @param fileId 文件编号
     */
    public VoiceDo(String path, String fileId) {
        this.voicePath = path;
        this.fileId = fileId;
    }

    /**
     * 语音识别
     */
    public void setComment() {
        log.info("voice recognize start");

        try {
            String params = "{\"ProjectId\":1165413,"
                    + "\"SubServiceType\":2,"
                    + "\"EngSerViceType\":\"16k\","
                    + "\"SourceType\":1,"
                    + "\"VoiceFormat\":\"wav\","
                    + "\"UsrAudioKey\":\"%s\","
                    + "\"Data\":\"%s\","
                    + "\"DataLen\":%d}";
            byte[] data = Util.readFileByBytes(this.voicePath);
            String voiceString = new BASE64Encoder().encode(reSamplingPCM(data));
            params = String.format(params, UUID.randomUUID().toString(), voiceString,
                    voiceString.length());
            SentenceRecognitionRequest req = SentenceRecognitionRequest
                    .fromJsonString(params, SentenceRecognitionRequest.class);
            SentenceRecognitionResponse resp = ASR_CLIENT.SentenceRecognition(req);
            this.speechData = resp;
            System.out.println(speechData);
            log.info("voice recognize finished");
            setScore();
        } catch (Exception e) {
            log.error("voice recognize error，exception is {}", e.getStackTrace());
            throw new BusinessException(VOICE_COMMENT_CAN_NOT_RECOGNIZED);
        }
    }

    /**
     * 语音文件转码
     *
     * @param data 文件字节码
     * @return 转码后的字节码
     */
    private static byte[] reSamplingPCM(byte[] data) {

        log.info("reSampling start");
        try (AudioInputStream audioIn =
                     AudioSystem.getAudioInputStream(new ByteArrayInputStream(data));
             AudioInputStream convertedStream =
                     AudioSystem.getAudioInputStream(AUDIO_FORMAT, audioIn);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            if (audioIn.getFormat().matches(AUDIO_FORMAT)) {
                return data;
            }
            byte[] buff = new byte[SAMPLE_RATE / 2];
            int numReads = -1;
            while ((numReads = convertedStream.read(buff)) != -1) {
                outputStream.write(buff);
            }

            log.info("reSampling finish");
            return outputStream.toByteArray();
        } catch (UnsupportedAudioFileException | IOException e) {
            log.error("occurs errors when re-sampling the audio stream:{}", e.getStackTrace());
            throw new BusinessException(VOICE_COMMENT_CAN_NOT_RECOGNIZED);
        }
    }

    /**
     * 情感分析
     */
    private void setScore() {
        try {
            log.info("sentiment analyze start");
            String params = "{\"Text\":\"%s\"}";
            String texts = this.speechData.getResult();
            params = String.format(params, texts);
            System.out.println(params);
            SentimentAnalysisRequest req = SentimentAnalysisRequest
                    .fromJsonString(params, SentimentAnalysisRequest.class);
            SentimentAnalysisResponse resp = NLP_CLIENT.SentimentAnalysis(req);
            System.out.println(resp);
            this.nlpData = resp;
            this.score = BigDecimal.valueOf(resp.getPositive()).movePointRight(1).intValue();
            log.info("sentiment analyze success");
        } catch (Exception e) {
            SentimentAnalysisResponse response = new SentimentAnalysisResponse();
            response.setNegative(0.5F);
            response.setPositive(0.5F);
            response.setSentiment("neutral");
            this.setNlpData(response);
            this.score = 5;
            log.error("sentiment analyze error, exception is {}", e.getStackTrace());
        }
    }
}
