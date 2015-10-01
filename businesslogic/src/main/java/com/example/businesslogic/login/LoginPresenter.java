package com.example.businesslogic.login;

import android.util.Patterns;

import com.example.businesslogic.R;
import com.example.businesslogic.framework.BasePresenter;
import com.example.gateway.factory.GateWayFactory;
import com.example.gateway.framework.network.ErrorMessage;
import com.example.gateway.framework.network.FailureMessage;
import com.example.gateway.framework.network.IResponseData;
import com.example.gateway.framework.network.OnRequestFinishedListener;
import com.example.gateway.login.ILoginRepository;
import com.example.gateway.login.ILoginService;
import com.example.gateway.models.Credential;
import com.example.gateway.models.LoginInfo;

public class LoginPresenter extends BasePresenter implements ILoginPresenter {

    private static final int MIN_PASSWORD_LENGTH = 6;

    private ILoginView loginView;
    private boolean isValidEmail;
    private boolean isValidPassword;

    private ILoginService loginService;
    private ILoginRepository loginRepository;

    private LoginInfo loginInfo;

    public LoginPresenter(ILoginView loginView) {
        super(loginView);
        this.loginView = loginView;
        loginService = GateWayFactory.getServiceFactory().getLoginService();
        loginRepository = GateWayFactory.getRepositoryFactory().getLoginRepository();
    }

    @Override
    public boolean isValidEmail(String email) {
        isValidEmail = email != null && !email.trim().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        return isValidEmail;
    }

    @Override
    public boolean isValidPassword(String password) {
        isValidPassword = password != null && password.trim().length() >= MIN_PASSWORD_LENGTH;
        return isValidPassword;
    }

    @Override
    public void updateSignInButtonState() {
        boolean isSignInReady = isValidEmail && isValidPassword;
        loginView.updateSignInButtonState(isSignInReady);
    }

    @Override
    public void doSignIn(String email, String password) {
        showProgressDialog(getContext().getString(R.string.please_wait));

        loginService.addServiceListener(OnLoginRequestListener);
        loginService.doLoginRequest(new Credential(email, password));
    }


    private OnRequestFinishedListener OnLoginRequestListener = new OnRequestFinishedListener() {
        @Override
        public void onSuccess(IResponseData successData) {
            //TODO Handle on login success
        }

        @Override
        public void onFailure(IResponseData failureData) {
            handleFailureData(failureData);
        }
    };

    private void handleFailureData(IResponseData failureData) {
        hideProgressDialog();
        loginRepository.logOut();
        ErrorMessage errorMessage = (ErrorMessage) failureData;
        FailureMessage failureMessage = errorMessage.getErrors();
        showMessageDialog(failureMessage.getDetails());
    }

    @Override
    public void releaseResources() {
        loginService.removeServiceListener(OnLoginRequestListener);
        loginView = null;
    }
}
