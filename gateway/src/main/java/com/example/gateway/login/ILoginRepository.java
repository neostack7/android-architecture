package com.example.gateway.login;

import com.example.gateway.models.LoginInfo;

public interface ILoginRepository {

    void saveLoginInfo(LoginInfo loginInfo);

    LoginInfo getLoginInfo();

    boolean isLoggedIn();

    void logOut();
}
