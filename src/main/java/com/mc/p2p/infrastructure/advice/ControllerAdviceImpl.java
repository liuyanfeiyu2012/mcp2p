package com.mc.p2p.infrastructure.advice;

import com.mc.p2p.infrastructure.common.MillisDateEditor;
import com.mc.p2p.infrastructure.common.RespVo;
import com.mc.p2p.infrastructure.common.StripNumberEditor;
import com.mc.p2p.infrastructure.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @auther: 谢星星
 * @Date: 2019/11/30 20:35
 * @Description:
 */
@ControllerAdvice
public class ControllerAdviceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerAdviceImpl.class);

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new MillisDateEditor());
        Stream.of(Byte.class,
                Short.class,
                Integer.class,
                Long.class,
                Float.class,
                Double.class,
                BigInteger.class,
                BigDecimal.class)
                .forEach(numberClass ->
                        webDataBinder.registerCustomEditor(numberClass,
                                new StripNumberEditor(numberClass)));
    }

    /**
     * 参数异常
     *
     * @param ex
     * @return RespVo
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public RespVo handlerBusinessException(BindException ex) {
        return new RespVo<>(RespVo.FAIL_CODE, ex.getFieldError().getDefaultMessage(), null);
    }

    /**
     * 参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RespVo handlerBusinessException(MethodArgumentNotValidException ex) {
        return new RespVo<>(RespVo.FAIL_CODE,
                ex.getBindingResult().getFieldError().getDefaultMessage(),
                null);
    }

    /**
     * 参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public RespVo handlerBusinessException(ConstraintViolationException ex) {
        return new RespVo<>(RespVo.FAIL_CODE,
                ex.getConstraintViolations().iterator().next().getMessage(),
                null);
    }

    /**
     * 业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public RespVo handlerBusinessException(BusinessException ex) {
        LOG.error(" BusinessException " + ex.getResMsg(), ex);
        return ex.toRespVo();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public RespVo handlerIllegalArgumentException(IllegalArgumentException ex) {
        LOG.error("error", ex);
        return RespVo.FAIL();
    }

    /**
     * 其它异常
     *
     * @param th
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public RespVo handlerThrowable(Throwable th) {
        LOG.error("error", th);
        return RespVo.FAIL();
    }

}
