package com.example.businesslogic.audioplayer;

import com.example.businesslogic.framework.BasePresenter;
import com.example.gateway.factory.GateWayFactory;
import com.example.gateway.framework.network.IResponseData;
import com.example.gateway.framework.network.OnFileDownloadFinishedListener;
import com.example.gateway.framework.network.OnRequestFinishedListener;
import com.example.gateway.models.FileUploadResponse;
import com.example.gateway.playaudio.IAudioFileRepository;
import com.example.gateway.playaudio.IDownloadAudioService;
import com.example.gateway.playaudio.IUploadAudioService;

import java.io.IOException;

import retrofit.client.Response;

/**
 *
 */
public class PlayAudioPresenter extends BasePresenter implements IPlayAudioPresenter {

    private IAudioPlayerView audioPlayerView;

    private IDownloadAudioService downloadAudioService;
    private IAudioFileRepository audioRepository;

    private IUploadAudioService uploadAudioService;


    private String downloadAudioFileName;
    private String uploadFileName;


    private OnFileDownloadFinishedListener onFileDownloadFinishedListener = new OnFileDownloadFinishedListener() {

        @Override
        public void onSuccess(Response success) {
            try {
                audioRepository.saveAudioFile(downloadAudioFileName, success.getBody().in());
                audioPlayerView.startAudioPlayerService(audioPlayerView.getFilePath(downloadAudioFileName));
                audioPlayerView.enableUploadBtn(true);
                audioPlayerView.hideProgressDialog();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IResponseData failure) {
            //TODO HANDLE on failure downloading audio file
        }
    };

    private OnRequestFinishedListener onFileUploadListener = new OnRequestFinishedListener<FileUploadResponse>() {
        @Override
        public void onSuccess(FileUploadResponse successData) {
            audioPlayerView.hideProgressDialog();
            audioPlayerView.showUploadSuccessMessage(successData);
        }

        @Override
        public void onFailure(IResponseData failureData) {

        }
    };

    public PlayAudioPresenter(IAudioPlayerView audioPlayerView) {
        super(audioPlayerView);
        this.audioPlayerView = audioPlayerView;
        downloadAudioService = GateWayFactory.getServiceFactory().getDownloadAudioService();
        uploadAudioService = GateWayFactory.getServiceFactory().getUploadAudioService();
        audioRepository = GateWayFactory.getRepositoryFactory().getFileRepository();
    }

    @Override
    public void playAudioFile(String filename) {
        audioPlayerView.showProgressDialog("Downloading audio.Please wait...");
        this.downloadAudioFileName = filename;
        downloadAudioService.addDownloadServiceListener(onFileDownloadFinishedListener);
        downloadAudioService.downloadAudioService(filename);

    }

    @Override
    public void uploadAudioFile(String filename) {
        audioPlayerView.showProgressDialog("Uploading audio.Please wait...");
        this.uploadFileName = filename;
        uploadAudioService.addServiceListener(onFileUploadListener);
        uploadAudioService.uploadAudioService(filename, audioRepository.getFile(filename));
    }

    @Override
    public void releaseResources() {
        audioRepository.deleteFile(downloadAudioFileName);
        downloadAudioService.removeDownloadServiceListener(onFileDownloadFinishedListener);
        audioPlayerView = null;
    }
}
