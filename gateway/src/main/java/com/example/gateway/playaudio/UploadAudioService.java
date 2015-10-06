package com.example.gateway.playaudio;

import android.content.Context;

import com.example.gateway.factory.RestClient;
import com.example.gateway.framework.network.ErrorHandler;
import com.example.gateway.framework.network.OnRequestFinishedListener;
import com.example.gateway.framework.network.RestService;
import com.example.gateway.framework.network.UploadErrorMessage;
import com.example.gateway.models.FileUploadResponse;

import java.io.File;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Created by PraveenKatha on 02/10/15.
 */
public class UploadAudioService extends RestService implements IUploadAudioService {

    private final Context context;

    public UploadAudioService(Context context) {
        this.context = context;
    }

    @Override
    public void uploadAudioService(String filename,File audioFile) {
        System.out.println("---------------------"+filename + " "+audioFile.exists());
        RestClient.getInstance().getEndpointsForUpload().uploadAudio(".ABDUCTION", new TypedFile("multipart/form-data", audioFile), new Callback<FileUploadResponse>() {
            @Override
            public void success(FileUploadResponse response, Response response2) {
                for (OnRequestFinishedListener listener : listeners) {
                    listener.onSuccess(response);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                UploadErrorMessage errorMessage = ErrorHandler.getUploadErrorMessage(error);
                for (OnRequestFinishedListener listener : listeners) {
                    listener.onFailure(errorMessage);
                }
            }
        });
    }

}
