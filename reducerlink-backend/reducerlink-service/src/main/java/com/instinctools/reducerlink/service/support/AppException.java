package com.instinctools.reducerlink.service.support;

public class AppException extends RuntimeException {
    private static final long serialVersionUID = 0L;

    private String errorCode;
    private Object data;

    public AppException(String errorCode) {
        this(errorCode, null);
    }

    public AppException(String errorCode, Object data) {
        super(errorCode);

        this.errorCode = errorCode;
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object getData() {
        return data;
    }

}
