package com.example.gateway.framework.network;

/**
 *
 */
public interface  OnRequestFinishedListener<E extends IResponseData> {

    void onSuccess(E successData);

    void onFailure(IResponseData failureData);
}
