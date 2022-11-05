package com.bt.exception.custom;

import com.bt.constant.ErrorStatus;

/**
 * @description:
 * @author: jlm
 * @time: 2021/6/18 18:43
 */
public class CommonException extends RuntimeException{
    private static final long serialVersionUID = 4061732202885069884L;
    /**
     * 错误码
     */
    private int errorCode;

    public CommonException(ErrorStatus errorStatus) {
        this(errorStatus.getMessage(), null, false, false);
        this.errorCode = errorStatus.getValue();
    }

    public CommonException(String msg) {
        this(msg, null, false, false);
        this.errorCode = 500;
    }

    public CommonException(int code, String msg) {
        this(msg, null, false, false);
        this.errorCode = code;
    }

    protected CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
