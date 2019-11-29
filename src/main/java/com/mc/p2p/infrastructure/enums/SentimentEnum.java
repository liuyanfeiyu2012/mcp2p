package com.mc.p2p.infrastructure.enums;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

@Getter
public enum SentimentEnum {
    TEN(10,"爱了(//▽//)"),
    NINE(9,"爱了(//▽//)"),
    EIGHT(8,"哎吆，不错哦(•‾̑⌣‾̑•)"),
    SEVEN(7,"哎吆，不错哦(•‾̑⌣‾̑•)"),
    SIX(6,"吃瓜٩(๑´0`๑)۶"),
    FIVE(5,"吃瓜٩(๑´0`๑)۶"),
    FOUR(4,"emmm( ´Д`)"),
    THREE(3,"emmm( ´Д`)"),
    TWO(2,"打扰了(￣^￣)"),
    ONE(1,"打扰了(￣^￣)"),
    ZERO(0,"打扰了(￣^￣)"),
    ;

    Integer score;
    String chineseType;

    SentimentEnum(Integer score, String chineseType){
        this.score = score;
        this.chineseType = chineseType;
    }

    public static String findChineseType(Integer score){
        return Arrays.stream(SentimentEnum.values())
                .filter(sentimentEnum -> sentimentEnum.score.equals(score))
                .findAny()
                .map(SentimentEnum::getChineseType)
                .orElse(null);
    }
}
