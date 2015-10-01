package com.example.gateway.framework.network;

/**
 *
 */
public interface IService {

    void addServiceListener(OnRequestFinishedListener listener);

    void removeServiceListener(OnRequestFinishedListener listener);
}
