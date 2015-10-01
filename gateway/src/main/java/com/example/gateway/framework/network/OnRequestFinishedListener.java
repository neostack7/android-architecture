package com.example.gateway.framework.network;

/**
 *
 */
public interface OnRequestFinishedListener {

    void onSuccess(IResponseData successData);

    void onFailure(IResponseData failureData);
}
