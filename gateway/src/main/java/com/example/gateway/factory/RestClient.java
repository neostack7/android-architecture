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
     * "X-Parse-Application-Id": "VzxoXPuApFUCNOyOdUp4zTjtzviccPDx6aQRKNkt",
     * "X-Parse-REST-API-Key": "jP1kswcyMCQbFOryoDGKktLzYOeW7IEyjaxNIePR",
     * "Content-Type": "image/jpeg"
     *
     * @return Endpoints
     */
    public Endpoints getEndpointsForUpload() {
        final RestAdapter.Builder builder = getBasicRestAdapterBuilder();
        builder.setEndpoint(EndPointProperties.getInstance().getUploadUrl());
        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("X-Parse-Application-Id","VzxoXPuApFUCNOyOdUp4zTjtzviccPDx6aQRKNkt");
                request.addHeader("X-Parse-REST-API-Key","jP1kswcyMCQbFOryoDGKktLzYOeW7IEyjaxNIePR");
                request.addHeader("Content-Type", "audio/wav");

            }
        });
        return builder.build().create(Endpoints.class);
    }

    /**
     * @return Endpoints
     */
    public Endpoints getEndpoints() {
        final RestAdapter.Builder builder = getBasicRestAdapterBuilder();
        builder.setEndpoint(EndPointProperties.getInstance().getBaseUrl());
        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Content-Type", "application/json");
            }
        });
        return builder.build().create(Endpoints.class);
    }


    /**
     * @return Returns Basic RestAdapter Builder
     */
    private RestAdapter.Builder getBasicRestAdapterBuilder() {
        final RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        return builder;
    }

    public Endpoints getBasicEndpoints() {
        final RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setEndpoint(EndPointProperties.getInstance().getBaseUrl());
        final RestAdapter adapter = builder.build();
        return adapter.create(Endpoints.class);
    }


}
