package com.example.gateway.framework.network;

/**
 * Created by PraveenKatha on 06/10/15.
 */
public class UploadErrorMessage implements IResponseData{
    private String errorCode;
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
