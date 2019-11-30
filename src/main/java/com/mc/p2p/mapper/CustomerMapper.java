package com.mc.p2p.mapper;

import com.mc.p2p.model.po.Customer;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
public interface CustomerMapper extends Mapper<Customer>, MySqlMapper<Customer> {
}