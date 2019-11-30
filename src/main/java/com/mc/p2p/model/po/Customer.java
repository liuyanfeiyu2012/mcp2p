package com.mc.p2p.model.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer")
public class Customer {
    /**
     * wx OpenId
     */
    @Id
    @Column(name = "open_id")
    @GeneratedValue(generator = "JDBC")
    private String openId;

    /**
     * 微信名称
     */
    @Column(name = "wx_name")
    private String wxName;

    /**
     * 微信头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 城市
     */
    private String city;

    /**
     * 获取wx OpenId
     *
     * @return open_id - wx OpenId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置wx OpenId
     *
     * @param openId wx OpenId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
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
     * 获取微信头像
     *
     * @return avatar - 微信头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置微信头像
     *
     * @param avatar 微信头像
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
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }
}