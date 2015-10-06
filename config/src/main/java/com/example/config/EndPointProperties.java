package com.example.config;

public class EndPointProperties {

    private static EndPointProperties instance = null;
    private String baseUrl;
    private String forgotPasswordUrl;
    private String uploadUrl = "https://api.parse.com/1/";

    private EndPointProperties() {
    }

    public static EndPointProperties getInstance() {
        if (instance == null) {
            instance = new EndPointProperties();
        }
        return instance;
    }

    //TODO Set Base URL
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl + "iq/";
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }
}
