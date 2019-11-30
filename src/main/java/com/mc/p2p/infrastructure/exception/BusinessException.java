package com.mc.p2p.infrastructure.exception;

import com.mc.p2p.infrastructure.common.RespVo;
import com.mc.p2p.infrastructure.enums.ResponseEnum;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private String resCode;

    private String resMsg;

    public BusinessException(RespVo responseVo) {
        super(responseVo.getResMsg());
        this.resCode = responseVo.getResCode();
        this.resMsg = responseVo.getResMsg();
    }

    public BusinessException(String resCode, String resMsg) {
        super(resMsg);
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public BusinessException(String resCode, String resMsg, String message) {
        super(message);
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public BusinessException(String resMsg) {
        super(resMsg);
        this.resCode = RespVo.FAIL_CODE;
        this.resMsg = resMsg;
    }

    public BusinessException(ResponseEnum ex) {
        super(ex.getDesc());
        this.resCode = ex.getCode();
        this.resMsg = ex.getDesc();
    }

    public RespVo toRespVo() {
        return new RespVo<>(this.resCode, this.resMsg, null);
    }

}
