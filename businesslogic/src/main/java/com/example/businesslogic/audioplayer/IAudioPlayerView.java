package com.example.businesslogic.audioplayer;

import com.example.businesslogic.framework.IBaseView;
import com.example.gateway.models.FileUploadResponse;

/**
 *
 */
public interface IAudioPlayerView extends IBaseView{
    void startAudioPlayerService(String absolutePath);
    String getFilePath(String filename);
    void enableUploadBtn(boolean enable);
    void showUploadSuccessMessage(FileUploadResponse response);
}
