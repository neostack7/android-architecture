package com.example.businesslogic.login;

import com.example.businesslogic.framework.IBasePresenter;

public interface ILoginPresenter extends IBasePresenter {

    boolean isValidEmail(final String email);

    boolean isValidPassword(final String password);

    void updateSignInButtonState();

    void doSignIn(final String email, final String password);

}
