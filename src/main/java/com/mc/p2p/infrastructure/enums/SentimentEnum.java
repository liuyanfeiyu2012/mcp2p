package com.mc.p2p.infrastructure.enums;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

@Getter
public enum SentimentEnum {
    POSITIVE("positive","正向"),
    NEGATIVE("negative","负向");

    String englishType;
    String chineseType;

    SentimentEnum(String englishType, String chineseType){
        this.englishType = englishType;
        this.chineseType = chineseType;
    }

    public static String findChineseType(String englishType){
        return Arrays.stream(SentimentEnum.values())
                .filter(sentimentEnum -> StringUtils.equals(sentimentEnum.getEnglishType(),englishType))
                .findAny()
                .map(SentimentEnum::getChineseType)
                .orElse(null);
    }
}
