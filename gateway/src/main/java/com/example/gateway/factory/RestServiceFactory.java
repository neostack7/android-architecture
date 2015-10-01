package com.example.gateway.factory;

import android.content.Context;

import com.example.gateway.login.ILoginService;
import com.example.gateway.login.LoginService;
import com.example.gateway.playaudio.DownloadAudioService;
import com.example.gateway.playaudio.IDownloadAudioService;

public class RestServiceFactory implements IRestServiceFactory {

    private Context context;
    private ILoginService loginService;
    private IDownloadAudioService downloadAudioService;

    public RestServiceFactory(Context context) {
        this.context = context;
    }

    @Override
    public ILoginService getLoginService() {
        if (loginService == null) {
            loginService = new LoginService(context);
        }
        return loginService;
    }

    @Override
    public IDownloadAudioService getDownloadAudioService() {
        if(downloadAudioService == null) {
            downloadAudioService = new DownloadAudioService(context);
        }
        return downloadAudioService;
    }

}
