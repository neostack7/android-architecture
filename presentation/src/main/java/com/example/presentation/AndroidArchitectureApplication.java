package com.example.presentation;

import android.app.Application;

import com.example.androidarchitecture.BuildConfig;
import com.example.config.EndPointProperties;
import com.example.gateway.factory.GateWayFactory;
import com.example.gateway.factory.RepositoryFactory;
import com.example.gateway.factory.RestServiceFactory;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class AndroidArchitectureApplication extends Application {

    private static AndroidArchitectureApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // Initialize Repository Factory
        GateWayFactory.initRepositoryFactory(new RepositoryFactory(getApplicationContext()));

        // Initialize Network Service Factory
        GateWayFactory.initNetworkServiceFactory(new RestServiceFactory(getApplicationContext()));

        // initialize End points URL
        copyEndPoints();

        // Enable cooking handling so requests after login can succeed
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
    }

    private void copyEndPoints() {
        EndPointProperties.getInstance().setBaseUrl(BuildConfig.BASE_ENDPOINT);
    }

    public static AndroidArchitectureApplication getInstance() {
        return instance;
    }

    @Override
    public void onTerminate() {
        // Destroy Repository Factory
        GateWayFactory.destroyRepositoryFactory();

        super.onTerminate();
    }
}
