package com.mc.p2p.domain.customer.repository;

import com.mc.p2p.mapper.CustomerMapper;
import com.mc.p2p.model.po.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : Yuan.Pan 2019/11/26 11:05 AM
 */
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer selectOne(String openId) {
        return customerMapper.selectByPrimaryKey(openId);
    }

    @Override
    public void register(Customer loginCustomer) {
        customerMapper.insertSelective(loginCustomer);
    }
}
