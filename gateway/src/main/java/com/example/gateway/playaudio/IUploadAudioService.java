package com.example.gateway.playaudio;

import com.example.gateway.framework.network.IService;

import java.io.File;

/**
 * Created by PraveenKatha on 02/10/15.
 */
public interface IUploadAudioService extends IService {
    void uploadAudioService(String filename,File audioFile);
}

