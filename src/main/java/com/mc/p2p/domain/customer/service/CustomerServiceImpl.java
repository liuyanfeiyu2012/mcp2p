package com.mc.p2p.domain.customer.service;

import com.google.common.collect.Lists;
import com.mc.p2p.domain.customer.entity.CustomerDo;
import com.mc.p2p.domain.customer.repository.CustomerRepository;
import com.mc.p2p.domain.video.service.VideoService;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import com.mc.p2p.infrastructure.exception.BusinessException;
import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.vo.CustomerCentreResp;
import com.mc.p2p.model.vo.LoginReq;
import com.mc.p2p.model.vo.VideoQueryResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : Yuan.Pan 2019/11/26 11:01 AM
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerRepository customerRepository;

    @Resource
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
        if (StringUtils.isBlank(openId)) {
            throw new BusinessException(ResponseEnum.LOGIN_LIMIT);
        }

        List<VideoQueryResp> videoList = videoService.selectVideoList(openId);
        Integer likeCount = videoService.likeCount(openId);

        return new CustomerCentreResp(videoList, Lists.newArrayList(), likeCount);
    }

    @Override
    public Customer selectByOpenId(String openId) {
        return customerRepository.selectOne(openId);
    }
}
