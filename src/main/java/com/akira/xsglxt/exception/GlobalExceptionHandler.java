package com.akira.xsglxt.exception;

import com.akira.xsglxt.common.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数验证异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
        return Result.fail("参数验证失败: " + message);
    }

    /**
     * 通用异常处理
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        e.printStackTrace();
        return Result.fail("系统异常: " + e.getMessage());
    }
}
