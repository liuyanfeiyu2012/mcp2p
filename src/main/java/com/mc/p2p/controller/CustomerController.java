package com.mc.p2p.controller;

import com.mc.p2p.domain.customer.service.CustomerService;
import com.mc.p2p.infrastructure.common.RespVo;
import com.mc.p2p.model.vo.CustomerCentreResp;
import com.mc.p2p.model.vo.LoginReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : Yuan.Pan 2019/11/26 10:52 AM
 */
@Api(tags = "用户接口API")
@Validated
@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public RespVo login(@Valid @RequestBody LoginReq request) {
        customerService.login(request);
        return RespVo.SUCCESS();
    }

    @ApiOperation("用户中心接口")
    @PostMapping("/centre")
    public RespVo<CustomerCentreResp> centre(String openId) {
        return RespVo.SUCCESS(customerService.centre(openId));
    }
}
