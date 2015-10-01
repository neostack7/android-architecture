package com.example.gateway.models;

import com.example.gateway.framework.network.IResponseData;

/**
 *
 */
public class LoginInfo implements IResponseData {
    private boolean loggedIn;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String username;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUsername() {
        return username;
    }
}
