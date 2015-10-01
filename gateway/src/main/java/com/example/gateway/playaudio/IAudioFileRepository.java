package com.example.gateway.playaudio;

import java.io.InputStream;

/**
 *
 */
public interface IAudioFileRepository {

    void saveAudioFile(String name,InputStream is);
    void deleteFile(String filename);
}
