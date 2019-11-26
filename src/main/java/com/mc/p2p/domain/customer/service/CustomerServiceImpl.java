package com.mc.p2p.domain.customer.service;

import com.mc.p2p.model.vo.CustomerCentreResp;
import com.mc.p2p.model.vo.LoginReq;
import org.springframework.stereotype.Service;

/**
 * @author : Yuan.Pan 2019/11/26 11:01 AM
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public void login(LoginReq request) {

    }

    @Override
    public CustomerCentreResp centre(String openId) {
        return null;
    }
}
