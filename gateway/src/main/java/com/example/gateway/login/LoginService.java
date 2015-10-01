package com.example.gateway.login;

import android.content.Context;

import com.example.gateway.factory.RestClient;
import com.example.gateway.framework.network.ErrorHandler;
import com.example.gateway.framework.network.ErrorMessage;
import com.example.gateway.framework.network.OnRequestFinishedListener;
import com.example.gateway.framework.network.RestService;
import com.example.gateway.models.Credential;
import com.example.gateway.models.LoginInfo;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginService extends RestService implements ILoginService {

    private Context context;

    public LoginService(Context context) {
        this.context = context;
    }

    @Override
    public void doLoginRequest(Credential credential) {

        RestClient.getInstance().getEndpoints().executeLoginRequest(credential, new Callback<LoginInfo>() {
            @Override
            public void success(LoginInfo loginInfo, Response response) {
                for (OnRequestFinishedListener listener : listeners) {
                    listener.onSuccess(loginInfo);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                ErrorMessage errorMessage = ErrorHandler.getErrorMessage(context, error);
                for (OnRequestFinishedListener listener : listeners) {
                    listener.onFailure(errorMessage);
                }
            }
        });
    }
}
