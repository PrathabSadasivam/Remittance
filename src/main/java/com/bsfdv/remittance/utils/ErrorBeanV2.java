package com.bsfdv.remittance.utils;

public enum ErrorBeanV2 {
    REQUIRED_FILE("A001", "File is required"),
    BAD_REQUEST("A002", "Bad Request"),
    NOT_FOUND("A003", "Content Not found"),
    INTERNAL_SERVER("A006", "Internal Server Error"),
    PARAM_INVALID("A011", "Parameter type invalid"),
    PARAM_MISSING("A012", "Parameter is missing"),
    MEDIATYPE_NOT_SUPPORT("A013", "MediaType not support"),
    PARAM_TYPE_INCORRECT("A024", "Parameter type is incorrect");

    private String errorCode;
    private String message;
    private ErrorBeanV2(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
    public String errorCode() {
        return errorCode;
    }
    public String message() {
        return message;
    }

}
