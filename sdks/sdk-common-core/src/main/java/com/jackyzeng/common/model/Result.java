package com.jackyzeng.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1L;
    private T data;
    private Integer code;
    private String message;
    private String localeMessage;
    @JsonIgnore
    private transient Object[] localeArgs;

    public static <T> Result<T> of(T datas, Integer code, String msg, Object... args) {
        return new Result<>(datas, code, msg, null, args);
    }

    /**
     * 不带数据的成功响应
     * 携带默认成功消息：success
     */
    public static <T> Result<T> success() {
        return of(null, CodeEnum.SUCCESS.getCode(), "success");
    }

    /**
     * 不带数据的成功响应
     * 携带指定消息的响应体
     */
    public static <T> Result<T> success(String msg, Object... args) {
        return of(null, CodeEnum.SUCCESS.getCode(), msg, args);
    }

    /**
     * 携带数据的响应体
     * 携带默认成功消息：success
     */
    public static <T> Result<T> succeed(T model) {
        return of(model, CodeEnum.SUCCESS.getCode(), "success");
    }

    /**
     * 携带数据的响应体
     * 携带待国际化的成功消息：msg｜args
     */
    public static <T> Result<T> succeed(T model, String msg, Object... args) {
        return of(model, CodeEnum.SUCCESS.getCode(), msg, args);
    }

    /**
     * 不带数据的失败响应
     * 携带失败消息的响应体
     */
    public static <T> Result<T> failure(String msg, Object... args) {
        return of(null, CodeEnum.ERROR.getCode(), msg, args);
    }

    /**
     * 携带数据的失败响应体
     * 携带待国际化的失败消息：msg｜args
     */
    public static <T> Result<T> failed(T model, String msg, Object... args) {
        return of(model, CodeEnum.ERROR.getCode(), msg, args);
    }

    public <D> D getDataOfClass(Class<D> clazz) {
        return clazz.cast(this.data);
    }

    public boolean hasError() {
        return CodeEnum.SUCCESS.getCode().intValue() != this.code.intValue();
    }
}
