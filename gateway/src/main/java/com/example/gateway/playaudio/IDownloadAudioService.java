package com.example.gateway.playaudio;

import com.example.gateway.framework.network.OnFileDownloadFinishedListener;


public interface IDownloadAudioService {
    void downloadAudioService(String filename);
    void addDownloadServiceListener(OnFileDownloadFinishedListener listener);
    void removeDownloadServiceListener(OnFileDownloadFinishedListener listener);
}
