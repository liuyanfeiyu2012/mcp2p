package com.mc.p2p.domain.customer.repository;

import com.mc.p2p.model.po.Customer;

/**
 * @auther: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
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
