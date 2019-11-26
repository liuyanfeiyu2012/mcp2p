package com.mc.p2p.domain.customer.repository;

import com.mc.p2p.model.po.Customer;

/**
 * @author : Yuan.Pan 2019/11/26 11:05 AM
 */
public interface CustomerRepository {

    Customer selectOne(String openId);

    void register(Customer loginCustomer);
}
