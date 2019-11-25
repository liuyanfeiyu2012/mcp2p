package com.mc.p2p.model.po;

import javax.persistence.*;

@Table(name = "bgm")
public class Bgm {
    /**
     * 业务主键
     */
    @Id
    @Column(name = "bgm_id")
    private String bgmId;

    /**
     * 背景音乐路径
     */
    @Column(name = "bgm_uri")
    private String bgmUri;

    /**
     * 背景音乐曲名
     */
    @Column(name = "bgm_name")
    private String bgmName;

    /**
     * 背景音乐背景图
     */
    @Column(name = "bgm_pic")
    private String bgmPic;

    /**
     * 获取业务主键
     *
     * @return bgm_id - 业务主键
     */
    public String getBgmId() {
        return bgmId;
    }

    /**
     * 设置业务主键
     *
     * @param bgmId 业务主键
     */
    public void setBgmId(String bgmId) {
        this.bgmId = bgmId;
    }

    /**
     * 获取背景音乐路径
     *
     * @return bgm_uri - 背景音乐路径
     */
    public String getBgmUri() {
        return bgmUri;
    }

    /**
     * 设置背景音乐路径
     *
     * @param bgmUri 背景音乐路径
     */
    public void setBgmUri(String bgmUri) {
        this.bgmUri = bgmUri;
    }

    /**
     * 获取背景音乐曲名
     *
     * @return bgm_name - 背景音乐曲名
     */
    public String getBgmName() {
        return bgmName;
    }

    /**
     * 设置背景音乐曲名
     *
     * @param bgmName 背景音乐曲名
     */
    public void setBgmName(String bgmName) {
        this.bgmName = bgmName;
    }

    /**
     * 获取背景音乐背景图
     *
     * @return bgm_pic - 背景音乐背景图
     */
    public String getBgmPic() {
        return bgmPic;
    }

    /**
     * 设置背景音乐背景图
     *
     * @param bgmPic 背景音乐背景图
     */
    public void setBgmPic(String bgmPic) {
        this.bgmPic = bgmPic;
    }
}