package com.example.gateway.models;


import com.example.gateway.framework.network.IResponseData;

public class Credential implements IResponseData {

    private String username;
    private String password;

    public Credential(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
