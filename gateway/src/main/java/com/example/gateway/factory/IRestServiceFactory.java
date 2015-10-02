package com.example.gateway.factory;


import com.example.gateway.login.ILoginService;
import com.example.gateway.playaudio.IDownloadAudioService;
import com.example.gateway.playaudio.IUploadAudioService;

public interface IRestServiceFactory {
    ILoginService getLoginService();
    IDownloadAudioService getDownloadAudioService();
    IUploadAudioService getUploadAudioService();
}
