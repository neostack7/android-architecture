package com.example.businesslogic.login;


import com.example.businesslogic.framework.IBaseView;

public interface ILoginView extends IBaseView {

    void updateSignInButtonState(boolean isSignInReady);
}
