package com.mc.p2p.domain.customer.service;

import com.mc.p2p.domain.customer.entity.CustomerDo;
import com.mc.p2p.domain.customer.repository.CustomerRepository;
import com.mc.p2p.domain.video.service.VideoService;
import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.vo.CustomerCentreResp;
import com.mc.p2p.model.vo.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Yuan.Pan 2019/11/26 11:01 AM
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VideoService videoService;

    @Override
    public void login(LoginReq request) {
        Customer customer = customerRepository.selectOne(request.getOpenId());
        if (customer != null) {
            return;
        }

        // add customer
        CustomerDo customerDo = new CustomerDo();
        Customer loginCustomer = customerDo.login(request);
        customerRepository.register(loginCustomer);
    }

    @Override
    public CustomerCentreResp centre(String openId) {
        return null;
    }
}
