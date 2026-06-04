package com.akira.xsglxt.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应结果
 *
 * @param <T> 数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    /** 响应数据 */
    private T data;

    /** 响应消息 */
    private String msg;


    // ==================== 成功响应 ====================

    public static <T> Result<T> ok() {
        return new Result<>(null, "success");
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data, "success");
    }

    public static <T> Result<T> ok(T data, String msg) {
        return new Result<>(data, msg);
    }

    // ==================== 失败响应 ====================

    public static <T> Result<T> fail(String msg) {
        return new Result<>(null,  msg);
    }


    public static <T> Result<T> fail(String msg, T data) {
        return new Result<>(data, msg);
    }
}
