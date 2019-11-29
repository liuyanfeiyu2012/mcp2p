package com.mc.p2p.domain.customer.repository;

import com.mc.p2p.model.po.Customer;

/**
 * @author : Yuan.Pan 2019/11/26 11:05 AM
 */
public interface CustomerRepository {

    /**
     * 选择用户
     *
     * @param openId 用户编号
     * @return 用户信息
     */
    Customer selectOne(String openId);

    /**
     * 注册
     * @param loginCustomer 登录用户信息
     */
    void register(Customer loginCustomer);
}
