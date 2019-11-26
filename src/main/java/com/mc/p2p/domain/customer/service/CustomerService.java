package com.mc.p2p.domain.customer.service;

import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.vo.CustomerCentreResp;
import com.mc.p2p.model.vo.LoginReq;

/**
 * @author : Yuan.Pan 2019/11/26 11:01 AM
 */
public interface CustomerService {

    void login(LoginReq request);

    CustomerCentreResp centre(String openId);

    Customer selectByOpenId(String openId);
}
