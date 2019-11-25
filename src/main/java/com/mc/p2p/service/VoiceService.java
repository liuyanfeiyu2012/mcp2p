package com.mc.p2p.service;

import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.util.Util;
import com.mc.p2p.utils.SJacksonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
public class VoiceService {

    //设置APPID/AK/SK
    public static final String APP_ID = "17832684";
    public static final String API_KEY = "uYore5u1woXsi60jOtA0M4mE";
    public static final String SECRET_KEY = "o1WrqtVVAoeRzIxByYK50p6C38RbM35d";

    public static final Integer SuccessCode = 0;

    public static final int SAMPLE_RATE = 16000;

    // 16-bit audio
    private static final int BYTES_PER_SAMPLE = 2;
    // 16-bit audio
    private static final int BITS_PER_SAMPLE = 16;
    private static final double MAX_16_BIT = 32768;
    private static final int SAMPLE_BUFFER_SIZE = 4096;

    private static final int MONO   = 1;
    private static final int STEREO = 2;
    private static final boolean LITTLE_ENDIAN = false;
    private static final boolean BIG_ENDIAN    = true;
    private static final boolean SIGNED        = true;
    private static final boolean UNSIGNED      = false;
    private static AudioFormat dstFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
            SAMPLE_RATE,
            BITS_PER_SAMPLE,
            MONO,
            BYTES_PER_SAMPLE,
            8000,
            LITTLE_ENDIAN);

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NlpData{
        Long log_id;
        String text;
        List<Item> items;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Item{
            Double positive_prob;
            Integer sentiment;
            Double confidence;
            Double negative_prob;
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpeechData{
        Integer err_no;
        String err_msg;
        String corpus_no;
        String sn;
        List<String> result;
    }
    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech speechClient = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        AipNlp nlpClient = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        speechClient.setConnectionTimeoutInMillis(2000);
        speechClient.setSocketTimeoutInMillis(60000);


        // 可选：设置网络连接参数
        nlpClient.setConnectionTimeoutInMillis(2000);
        nlpClient.setSocketTimeoutInMillis(60000);
        HashMap<String,Object> options = new HashMap<>();
        String path = "D:\\Download\\public\\happy.wav";
//        options.put("dev_pid","1737");
        options.put("rate",16000);
        JSONObject speechRes = new JSONObject();
        // 调用接口
        try {
            byte[] data = Util.readFileByBytes(path);
            System.out.println(data.length);
            speechRes= speechClient.asr(reSamplingPCM(data), "wav", 16000, options);
        }catch (Exception e){
            e.printStackTrace();
        }
        SpeechData speechData = SJacksonUtil.extractPojo(speechRes.toString(2),SpeechData.class);
        if(!SuccessCode.equals(speechData.getErr_no())){
            System.out.println(speechData);
            return;
        }
        System.out.println(speechData.result);
        // 调用接口
        for(String text : speechData.getResult()) {
//            String text = StringUtils.join(speechData.getResult(), "。");
            JSONObject nlpRes = nlpClient.sentimentClassify(text, null);
            NlpData nlpData = SJacksonUtil.extractPojo(nlpRes.toString(2), NlpData.class);
            System.out.println(BigDecimal.valueOf(nlpData.getItems().get(0).getPositive_prob()).movePointRight(1).intValue());
        }

    }
    public static byte[] reSamplingPCM(byte[] data) {

        try(AudioInputStream audioIn = AudioSystem.getAudioInputStream(new ByteArrayInputStream(data));
            AudioInputStream convertedStream = AudioSystem.getAudioInputStream(dstFormat, audioIn);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            if (audioIn.getFormat().matches(dstFormat)) {
                return data;
            }

            int numReads = -1;

            int BUFF_SIZE = SAMPLE_RATE / 2;

            byte[] buff = new byte[BUFF_SIZE];

            while ((numReads = convertedStream.read(buff)) != -1) {
//                log.info("read {} byte(s)", numReads);
                outputStream.write(buff);
            }
            return outputStream.toByteArray();
        } catch (UnsupportedAudioFileException | IOException e) {
//            log.error("occurs errors when re-sampling the audio stream:{}",e);
            throw new RuntimeException("occurs errors when re-sampling the audio stream:{}",e);
        }
    }
}
