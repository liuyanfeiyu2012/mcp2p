package com.mc.p2p.mapper;

import com.mc.p2p.model.po.Customer;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface CustomerMapper extends Mapper<Customer>, MySqlMapper<Customer> {
}