package com.example.gateway.framework.repository;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
public class FileStorage implements IFileStorage {

    private static FileStorage instance;
    private final Context context;

    private FileStorage(Context context) {
        this.context = context;
    }

    public static IFileStorage getInstance(Context context) {
        if (instance == null) {
            synchronized (FileStorage.class) {
                if (instance == null) {
                    instance = new FileStorage(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void saveFile(String filename, InputStream inputStream) {

        OutputStream outputStream = null;

        try {

            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            System.out.println("Done!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    @Override
    public void delete(String filename) {
        context.deleteFile(filename);
    }

    public File getFile(String filename) {
        return context.getFileStreamPath(filename);
    }

}
