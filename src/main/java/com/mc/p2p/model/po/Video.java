package com.mc.p2p.model.po;

import java.util.Date;
import javax.persistence.*;


/**
 * @auther: 谢星星
 * @Date: 2019/11/30 20:35
 * @Description:
 */
@Table(name = "video")
public class Video {
    /**
     * 业务主键
     */
    @Id
    @Column(name = "video_id")
    @GeneratedValue(generator = "JDBC")
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
     * 微信名称
     */
    @Column(name = "wx_name")
    private String wxName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 打分
     */
    private String score;

    /**
     * 描述
     */
    private String memo;

    /**
     * 所属动物
     */
    private String animal;

    /**
     * 状态0-审核中 1-审核成功
     */
    private String status;

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
     * 获取微信名称
     *
     * @return wx_name - 微信名称
     */
    public String getWxName() {
        return wxName;
    }

    /**
     * 设置微信名称
     *
     * @param wxName 微信名称
     */
    public void setWxName(String wxName) {
        this.wxName = wxName;
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

    /**
     * 获取打分
     *
     * @return score - 打分
     */
    public String getScore() {
        return score;
    }

    /**
     * 设置打分
     *
     * @param score 打分
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * 获取描述
     *
     * @return memo - 描述
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置描述
     *
     * @param memo 描述
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取所属动物
     *
     * @return animal - 所属动物
     */
    public String getAnimal() {
        return animal;
    }

    /**
     * 设置所属动物
     *
     * @param animal 所属动物
     */
    public void setAnimal(String animal) {
        this.animal = animal;
    }

    /**
     * 获取状态0-审核中 1-审核成功
     *
     * @return status - 状态0-审核中 1-审核成功
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态0-审核中 1-审核成功
     *
     * @param status 状态0-审核中 1-审核成功
     */
    public void setStatus(String status) {
        this.status = status;
    }
}