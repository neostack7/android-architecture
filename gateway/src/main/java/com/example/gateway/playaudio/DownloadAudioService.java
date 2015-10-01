package com.example.gateway.playaudio;

import android.content.Context;

import com.example.gateway.factory.RestClient;
import com.example.gateway.framework.network.ErrorHandler;
import com.example.gateway.framework.network.ErrorMessage;
import com.example.gateway.framework.network.OnFileDownloadFinishedListener;

import java.util.HashSet;
import java.util.Set;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DownloadAudioService implements IDownloadAudioService {

    private Context context;

    protected Set<OnFileDownloadFinishedListener> listeners = new HashSet<>();

    public DownloadAudioService(Context context) {
        this.context = context;
    }

    public void addDownloadServiceListener(OnFileDownloadFinishedListener listener) {
        listeners.add(listener);
    }

    public void removeDownloadServiceListener(OnFileDownloadFinishedListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    @Override
    public void downloadAudioService(String filename) {

        RestClient.getInstance().getEndpoints().downloadFile(filename, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                for (OnFileDownloadFinishedListener listener : listeners) {
                    listener.onSuccess(response);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                ErrorMessage errorMessage = ErrorHandler.getErrorMessage(context, error);
                for (OnFileDownloadFinishedListener listener : listeners) {
                    listener.onFailure(errorMessage);
                }
            }
        });
    }

}
