package com.mc.p2p.model.po;

import java.io.Serializable;

public class Video implements Serializable {
    private String videoId;

    private String videoUri;

    private String videoBgUri;

    private Double videoTime;

    private String videoMemo;

    private Integer likeCount;

    private String uid;

    private static final long serialVersionUID = 1L;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    public String getVideoBgUri() {
        return videoBgUri;
    }

    public void setVideoBgUri(String videoBgUri) {
        this.videoBgUri = videoBgUri;
    }

    public Double getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Double videoTime) {
        this.videoTime = videoTime;
    }

    public String getVideoMemo() {
        return videoMemo;
    }

    public void setVideoMemo(String videoMemo) {
        this.videoMemo = videoMemo;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}