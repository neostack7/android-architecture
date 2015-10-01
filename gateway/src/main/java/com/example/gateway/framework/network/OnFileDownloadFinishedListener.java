package com.example.gateway.framework.network;

import retrofit.client.Response;

/**
 *
 */
public interface OnFileDownloadFinishedListener {

    void onSuccess(Response success);

    void onFailure(IResponseData failure);
}
