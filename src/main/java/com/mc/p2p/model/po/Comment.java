package com.mc.p2p.model.po;

import java.util.Date;
import javax.persistence.*;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Table(name = "comment")
public class Comment {
    /**
     * 评论编号
     */
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(generator = "JDBC")
    private String commentId;

    /**
     * 视频编号
     */
    @Column(name = "video_id")
    private String videoId;

    /**
     * 用户编号
     */
    private String uid;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 音频路径
     */
    @Column(name = "voice_path")
    private String voicePath;

    /**
     * 音频内容
     */
    private String context;

    /**
     * 评论得分
     */
    private Integer score;

    /**
     * positive-正向，negative-负向，neutral-中性
     */
    private String sentiment;

    /**
     * 负向概率
     */
    private String negative;

    /**
     * 正向概率
     */
    private String positive;

    @Column(name = "comment_time")
    private Date commentTime;

    /**
     * 获取评论编号
     *
     * @return comment_id - 评论编号
     */
    public String getCommentId() {
        return commentId;
    }

    /**
     * 设置评论编号
     *
     * @param commentId 评论编号
     */
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取视频编号
     *
     * @return video_id - 视频编号
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 设置视频编号
     *
     * @param videoId 视频编号
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取用户编号
     *
     * @return uid - 用户编号
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置用户编号
     *
     * @param uid 用户编号
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取用户名
     *
     * @return uname - 用户名
     */
    public String getUname() {
        return uname;
    }

    /**
     * 设置用户名
     *
     * @param uname 用户名
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取音频路径
     *
     * @return voice_path - 音频路径
     */
    public String getVoicePath() {
        return voicePath;
    }

    /**
     * 设置音频路径
     *
     * @param voicePath 音频路径
     */
    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
    }

    /**
     * 获取音频内容
     *
     * @return context - 音频内容
     */
    public String getContext() {
        return context;
    }

    /**
     * 设置音频内容
     *
     * @param context 音频内容
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * 获取评论得分
     *
     * @return score - 评论得分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置评论得分
     *
     * @param score 评论得分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取positive-正向，negative-负向，neutral-中性
     *
     * @return sentiment - positive-正向，negative-负向，neutral-中性
     */
    public String getSentiment() {
        return sentiment;
    }

    /**
     * 设置positive-正向，negative-负向，neutral-中性
     *
     * @param sentiment positive-正向，negative-负向，neutral-中性
     */
    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    /**
     * 获取负向概率
     *
     * @return negative - 负向概率
     */
    public String getNegative() {
        return negative;
    }

    /**
     * 设置负向概率
     *
     * @param negative 负向概率
     */
    public void setNegative(String negative) {
        this.negative = negative;
    }

    /**
     * 获取正向概率
     *
     * @return positive - 正向概率
     */
    public String getPositive() {
        return positive;
    }

    /**
     * 设置正向概率
     *
     * @param positive 正向概率
     */
    public void setPositive(String positive) {
        this.positive = positive;
    }

    /**
     * @return comment_time
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * @param commentTime
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}