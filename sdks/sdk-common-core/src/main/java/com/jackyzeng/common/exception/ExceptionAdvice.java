package com.jackyzeng.common.exception;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jackyzeng.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@ControllerAdvice(basePackages = "com.jackyzeng.structure")
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public Result<String> handleException(BusinessException e) {
        return Result.failure(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        return defHandler("未知异常", e);
    }

    private Result<String> defHandler(String msg, Exception e) {
        log.error(msg, e);
        if (StringUtils.isBlank(msg)) {
            msg = e.toString();
        }
        return Result.failure(msg);
    }
}
