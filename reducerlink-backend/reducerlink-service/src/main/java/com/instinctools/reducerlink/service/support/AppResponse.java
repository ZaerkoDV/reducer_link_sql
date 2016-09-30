package com.instinctools.reducerlink.service.support;

public class AppResponse {
    public static final String STATUS_OK = "ok";
    public static final String STATUS_ERROR = "error";

    private String status;
    private String errorCode;
    private Object data;

    public AppResponse() {
    }

    public AppResponse(String status, String errorCode, Object data) {
        this.status = status;
        this.data = data;
        this.errorCode = errorCode;
    }

    public String getStatus() {
        return status;
    }

    public AppResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public AppResponse setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Object getData() {
        return data;
    }

    public AppResponse setData(Object data) {
        this.data = data;
        return this;
    }
}
