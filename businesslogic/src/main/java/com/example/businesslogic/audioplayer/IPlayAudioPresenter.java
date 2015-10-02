package com.example.businesslogic.audioplayer;

import com.example.businesslogic.framework.IBasePresenter;

/**
 *
 */
public interface IPlayAudioPresenter extends IBasePresenter{
    void playAudioFile(String filename);
    void uploadAudioFile(String filename);
}
