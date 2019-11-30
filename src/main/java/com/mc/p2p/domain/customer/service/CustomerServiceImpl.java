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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * 用户mapper
     */
    @Resource
    private CustomerRepository customerRepository;

    /**
     * 视频服务
     */
    @Resource
    private VideoService videoService;

    /**
     * 登录
     *
     * @param request 请求参数
     */
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

    /**
     * 用户中心
     * @param openId 用户编号
     * @return 用户信息
     */
    @Override
    public CustomerCentreResp centre(String openId) {
        if (StringUtils.isBlank(openId)) {
            throw new BusinessException(ResponseEnum.LOGIN_LIMIT);
        }

        List<VideoQueryResp> videoList = videoService.selectVideoList(openId);
        Integer likeCount = videoService.likeCount(openId);

        return new CustomerCentreResp(videoList, Lists.newArrayList(), likeCount);
    }

    /**
     * 选择用户
     * @param openId 用户编号
     * @return 用户信息
     */
    @Override
    public Customer selectByOpenId(String openId) {
        return customerRepository.selectOne(openId);
    }
}
