package com.mc.p2p.domain.customer.repository;

import com.mc.p2p.mapper.CustomerMapper;
import com.mc.p2p.model.po.Customer;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author : Yuan.Pan 2019/11/26 11:05 AM
 */
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    /**
     * 用户mapper
     */
    @Resource
    private CustomerMapper customerMapper;

    /**
     * 选择用户
     *
     * @param openId 用户编号
     * @return 用户信息
     */
    @Override
    public Customer selectOne(String openId) {
        return customerMapper.selectByPrimaryKey(openId);
    }

    /**
     * 注册
     * @param loginCustomer 登录用户信息
     */
    @Override
    public void register(Customer loginCustomer) {
        customerMapper.insertSelective(loginCustomer);
    }
}
