package com.mc.p2p.model.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "video")
public class Video {
    /**
     * 业务主键
     */
    @Id
    @Column(name = "video_id")
    private String videoId;

    /**
     * 视频路径
     */
    @Column(name = "video_uri")
    private String videoUri;

    /**
     * 视频背景图
     */
    @Column(name = "video_bg_uri")
    private String videoBgUri;

    /**
     * 视频时长
     */
    @Column(name = "video_time")
    private Double videoTime;

    /**
     * 视频说明
     */
    @Column(name = "video_memo")
    private String videoMemo;

    /**
     * 喜欢数
     */
    @Column(name = "like_count")
    private Integer likeCount;

    /**
     * 发布者ID
     */
    private String uid;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取业务主键
     *
     * @return video_id - 业务主键
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 设置业务主键
     *
     * @param videoId 业务主键
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取视频路径
     *
     * @return video_uri - 视频路径
     */
    public String getVideoUri() {
        return videoUri;
    }

    /**
     * 设置视频路径
     *
     * @param videoUri 视频路径
     */
    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    /**
     * 获取视频背景图
     *
     * @return video_bg_uri - 视频背景图
     */
    public String getVideoBgUri() {
        return videoBgUri;
    }

    /**
     * 设置视频背景图
     *
     * @param videoBgUri 视频背景图
     */
    public void setVideoBgUri(String videoBgUri) {
        this.videoBgUri = videoBgUri;
    }

    /**
     * 获取视频时长
     *
     * @return video_time - 视频时长
     */
    public Double getVideoTime() {
        return videoTime;
    }

    /**
     * 设置视频时长
     *
     * @param videoTime 视频时长
     */
    public void setVideoTime(Double videoTime) {
        this.videoTime = videoTime;
    }

    /**
     * 获取视频说明
     *
     * @return video_memo - 视频说明
     */
    public String getVideoMemo() {
        return videoMemo;
    }

    /**
     * 设置视频说明
     *
     * @param videoMemo 视频说明
     */
    public void setVideoMemo(String videoMemo) {
        this.videoMemo = videoMemo;
    }

    /**
     * 获取喜欢数
     *
     * @return like_count - 喜欢数
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 设置喜欢数
     *
     * @param likeCount 喜欢数
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 获取发布者ID
     *
     * @return uid - 发布者ID
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置发布者ID
     *
     * @param uid 发布者ID
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}