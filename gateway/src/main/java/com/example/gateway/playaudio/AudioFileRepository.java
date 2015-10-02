package com.example.gateway.playaudio;

import android.content.Context;

import com.example.gateway.framework.repository.FileStorage;
import com.example.gateway.framework.repository.IFileStorage;

import java.io.File;
import java.io.InputStream;

/**
 *
 */
public class AudioFileRepository implements IAudioFileRepository {

    IFileStorage fileStorage;

    public AudioFileRepository(Context context) {
        fileStorage = FileStorage.getInstance(context);
    }

    @Override
    public void saveAudioFile(String name, InputStream is) {
        fileStorage.saveFile(name,is);
    }

    @Override
    public void deleteFile(String filename) {
        fileStorage.delete(filename);
    }

    @Override
    public File getFile(String filename) {
        return fileStorage.getFile(filename);
    }


}
