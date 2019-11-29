package com.mc.p2p.infrastructure.enums;

import lombok.Getter;

import java.util.Arrays;

import static com.mc.p2p.infrastructure.constant.McConstant.*;

/**
 * 语音情感枚举
 */
@Getter
public enum SentimentEnum {
    /**
     * 10分
     */
    TEN(10, NINE_TEN),
    /**
     * 9分
     */
    NINE(9, NINE_TEN),
    /**
     * 8分
     */
    EIGHT(8, SEVEN_EIGHT),
    /**
     * 7分
     */
    SEVEN(7, SEVEN_EIGHT),
    /**
     * 6分
     */
    SIX(6, FIVE_SIX),
    /**
     * 5分
     */
    FIVE(5, FIVE_SIX),
    /**
     * 4分
     */
    FOUR(4, THREE_FOUR),
    /**
     * 3分
     */
    THREE(3, THREE_FOUR),
    /**
     * 2分
     */
    TWO(2, ZERO_TWO),
    /**
     * 1分
     */
    ONE(1, ZERO_TWO),
    /**
     * 0分
     */
    ZERO(0, ZERO_TWO),
    ;

    Integer score;
    String chineseType;

    SentimentEnum(Integer score, String chineseType) {
        this.score = score;
        this.chineseType = chineseType;
    }

    /**
     * 寻找情感名
     *
     * @param score 得分
     * @return 对应情感名
     */
    public static String findChineseType(Integer score) {
        return Arrays.stream(SentimentEnum.values())
                .filter(sentimentEnum -> sentimentEnum.score.equals(score))
                .findAny()
                .map(SentimentEnum::getChineseType)
                .orElse(null);
    }
}
