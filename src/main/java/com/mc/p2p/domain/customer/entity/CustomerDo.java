package com.mc.p2p.domain.customer.entity;

import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.vo.LoginReq;
import lombok.Data;

import java.util.Date;

/**
 * @author : Yuan.Pan 2019/11/26 11:03 AM
 */
@Data
public class CustomerDo {

    public Customer login(LoginReq request) {
        Customer record = new Customer();
        record.setOpenId(request.getOpenId());
        record.setWxName(request.getWxName());
        record.setAvatar(request.getAvatar());
        record.setCreateTime(new Date());
        return record;
    }
}
