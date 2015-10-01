package com.example.gateway.factory;

public class GateWayFactory {

    private static IRestServiceFactory sNetworkServiceFactory = null;
    private static IRepositoryFactory sRepositoryFactory = null;

    private GateWayFactory() {
    }

    public static IRestServiceFactory getServiceFactory() {
        return sNetworkServiceFactory;
    }

    public static void initNetworkServiceFactory(IRestServiceFactory factory) {
        sNetworkServiceFactory = factory;
    }

    public static IRepositoryFactory getRepositoryFactory() {
        return sRepositoryFactory;
    }

    public static void initRepositoryFactory(IRepositoryFactory factory) {
        sRepositoryFactory = factory;
    }

    public static void destroyRepositoryFactory() {
        if (sRepositoryFactory != null) {
            sRepositoryFactory.closeAllRealms();
        }
    }
}
