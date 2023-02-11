package com.lxx.mall.exception;

import com.lxx.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 林修贤
 * @date 2023/2/11
 * @description 处理统一异常的handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        log.error("Default Exception: ", e);
        return ApiRestResponse.error(LxxMallExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(LxxMallException.class)
    @ResponseBody
    public Object handleLxxMallException(LxxMallException e){
        log.error("LxxMallException: ", e);
        return ApiRestResponse.error(e.getCode(), e.getMessage());
    }
}
