package com.mc.p2p.exception;

import com.mc.p2p.common.RespVo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public RespVo toRespVo() {
        return new RespVo<>(this.resCode, this.resMsg, null);
    }

}
