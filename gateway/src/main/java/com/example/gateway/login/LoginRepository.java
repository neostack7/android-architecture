package com.example.gateway.login;

import android.content.Context;
import android.util.Log;

import com.example.gateway.framework.repository.IPreferenceStorage;
import com.example.gateway.framework.repository.PreferenceStorage;
import com.example.gateway.models.LoginInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 *
 */
public class LoginRepository implements ILoginRepository {

    private final String TAG = LoginRepository.class.getSimpleName();

    private IPreferenceStorage preferenceStorage;

    private enum Pref {
        LoginInfo,
        EulaDetails,
        StepData,
    }

    public LoginRepository(Context context) {
        this.preferenceStorage = PreferenceStorage.getInstance(context);
    }

    @Override
    public void saveLoginInfo(LoginInfo loginInfo) {
        if (loginInfo == null) {
            Log.e(TAG, "LoginInfo object is null.");
            return;
        }
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            String content = objectMapper.writeValueAsString(loginInfo);
            preferenceStorage.saveStringValue(Pref.LoginInfo.name(), content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LoginInfo getLoginInfo() {
        LoginInfo loginInfo = null;
        final ObjectMapper mapper = new ObjectMapper();
        String content = String.valueOf(preferenceStorage.getStringValue(Pref.LoginInfo.name()));
        try {
            loginInfo = mapper.readValue(content, LoginInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginInfo;
    }

    @Override
    public boolean isLoggedIn() {
        LoginInfo loginInfo = getLoginInfo();
        return loginInfo != null && loginInfo.isLoggedIn();
    }

    @Override
    public void logOut() {
        preferenceStorage.clearPreference(Pref.LoginInfo.name());
        preferenceStorage.clearAllPreferences();
        // TODO: clear all Realm data as well.
    }
}
