package com.example.businesslogic.audioplayer;

import com.example.businesslogic.framework.IBaseView;

/**
 *
 */
public interface IAudioPlayerView extends IBaseView{
    void startAudioPlayerService(String absolutePath);
    String getFilePath(String filename);
}
