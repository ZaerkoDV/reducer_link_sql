package com.instinctools.reducerlink.service.support;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult<T> {
    private T result;
    private String errorCode;
    private List<String> errorList;

    public ValidationResult() {
    }

    public T getResult() {
        return result;
    }

    public ValidationResult<T> setResult(T result) {
        this.result = result;
        this.errorCode = null;
        this.errorList = null;

        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ValidationResult<T> setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public ValidationResult<T> addError(String error) {
        if (errorList == null) {
            errorList = new ArrayList<String>();
        }

        errorList.add(error);
        return this;
    }

    public boolean isFaulted() {
        return (errorCode != null || (errorList != null && !errorList.isEmpty()));
    }

    public static <T> ValidationResult<T> fromErrorCode(String errorCode) {
        return (new ValidationResult<T>()).setErrorCode(errorCode);
    }

    public static <T> ValidationResult<T> fromResult(T result) {
        return (new ValidationResult<T>()).setResult(result);
    }
}
