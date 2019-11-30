package com.mc.p2p.domain.customer.entity;

import com.mc.p2p.model.po.Customer;
import com.mc.p2p.model.vo.LoginReq;
import lombok.Data;

import java.util.Date;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Data
public class CustomerDo {

    /**
     * 登录
     *
     * @param request 参数
     * @return 用户信息
     */
    public Customer login(LoginReq request) {
        final Customer record = new Customer();
        record.setOpenId(request.getOpenId());
        record.setWxName(request.getWxName());
        record.setAvatar(request.getAvatar());
        record.setCreateTime(new Date());
        return record;
    }
}
