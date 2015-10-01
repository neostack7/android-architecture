package com.example.gateway.factory;


import com.example.config.EndPointProperties;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;


public class RestClient {

    private static RestClient instance;

    private RestClient() {
    }

    // Singleton
    public static RestClient getInstance() {
        if (instance == null) {
            synchronized (RestClient.class) { // Double Locking
                if (instance == null) {
                    instance = new RestClient();
                }
            }
        }
        return instance;
    }

    /**
     *
     * @return Endpoints
     */
    public Endpoints getEndpoints() {
        final RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setEndpoint(EndPointProperties.getInstance().getBaseUrl());
        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Content-Type", "application/json");
            }
        });
        final RestAdapter adapter = builder.build();
        return adapter.create(Endpoints.class);
    }

    public Endpoints getBasicEndpoints() {
        final RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setEndpoint(EndPointProperties.getInstance().getBaseUrl());
        final RestAdapter adapter = builder.build();
        return adapter.create(Endpoints.class);
    }




}
