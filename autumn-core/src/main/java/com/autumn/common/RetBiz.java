package com.autumn.common;


import com.autumn.constant.ErrorStatus;
import org.apache.commons.lang3.StringUtils;

public class RetBiz<T> {


    //    1 成功
//    0 失败
    private Integer status;
    //返回信息
    private String error;
    //返回对象
    private T result;

    private Integer code;


    public Integer getStatus() {
        return StringUtils.isBlank(error) ? 1 : 0;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static RetBiz<String> error(String message) {
        RetBiz<String> retBiz = new RetBiz<>();
        retBiz.setError(message);
        retBiz.setCode(0);
        return retBiz;
    }

    public void setError(ErrorStatus errorStatus) {
        this.error = errorStatus.getMessage();
        this.code = errorStatus.getValue();
    }

    public static RetBiz<String> error(ErrorStatus errorStatus) {
        RetBiz<String> retBiz = new RetBiz<>();
        retBiz.setError(errorStatus.getMessage());
        retBiz.setCode(errorStatus.getValue());
        return retBiz;
    }

    public static RetBiz<String> OK() {
        RetBiz<String> retBiz = new RetBiz<>();
        retBiz.setResult(ErrorStatus.SUCCESS.getMessage());
        retBiz.setCode(ErrorStatus.SUCCESS.getValue());
        return retBiz;
    }

}
