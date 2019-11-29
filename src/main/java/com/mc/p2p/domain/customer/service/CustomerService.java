package com.mc.p2p.domain.customer.service;

import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.vo.CustomerCentreResp;
import com.mc.p2p.model.vo.LoginReq;

/**
 * @author : Yuan.Pan 2019/11/26 11:01 AM
 */
public interface CustomerService {

    /**
     * 登录
     *
     * @param request 请求参数
     */
    void login(LoginReq request);

    /**
     * 用户中心
     * @param openId 用户编号
     * @return 用户信息
     */
    CustomerCentreResp centre(String openId);

    /**
     * 选择用户
     * @param openId 用户编号
     * @return 用户信息
     */
    Customer selectByOpenId(String openId);
}
