package com.example.businesslogic.audioplayer;

import com.example.businesslogic.framework.BasePresenter;
import com.example.gateway.factory.GateWayFactory;
import com.example.gateway.framework.network.IResponseData;
import com.example.gateway.framework.network.OnFileDownloadFinishedListener;
import com.example.gateway.playaudio.IAudioFileRepository;
import com.example.gateway.playaudio.IDownloadAudioService;

import java.io.IOException;

import retrofit.client.Response;

/**
 *
 */
public class PlayAudioPresenter extends BasePresenter implements IPlayAudioPresenter {

    private final IAudioPlayerView audioPlayerView;
    private final IDownloadAudioService downloadAudioService;
    private final IAudioFileRepository audioRepository;
    private String filename;


    private OnFileDownloadFinishedListener onFileDownloadFinishedListener = new OnFileDownloadFinishedListener() {

        @Override
        public void onSuccess(Response success) {
            try {
                audioRepository.saveAudioFile(filename, success.getBody().in());
                audioPlayerView.startAudioPlayerService(audioPlayerView.getFilePath(filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IResponseData failure) {
            //TODO HANDLE on failure downloading audio file
        }
    };

    public PlayAudioPresenter(IAudioPlayerView audioPlayerView) {
        super(audioPlayerView);
        this.audioPlayerView = audioPlayerView;
        downloadAudioService = GateWayFactory.getServiceFactory().getDownloadAudioService();
        audioRepository = GateWayFactory.getRepositoryFactory().getFileRepository();
    }

    @Override
    public void playAudioFile(String filename) {
        this.filename = filename;
        downloadAudioService.addDownloadServiceListener(onFileDownloadFinishedListener);
        downloadAudioService.downloadAudioService(filename);

    }

    @Override
    public void releaseResources() {
        audioRepository.deleteFile(filename);
    }
}
