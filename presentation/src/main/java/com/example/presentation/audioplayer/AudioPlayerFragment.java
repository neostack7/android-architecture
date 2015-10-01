package com.example.presentation.audioplayer;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import com.example.androidarchitecture.R;
import com.example.businesslogic.audioplayer.IAudioPlayerView;
import com.example.businesslogic.audioplayer.IPlayAudioPresenter;
import com.example.businesslogic.audioplayer.PlayAudioPresenter;
import com.example.config.Constants;
import com.example.presentation.framework.BaseFragment;
import com.example.presentation.utils.AndroidUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

public class AudioPlayerFragment extends BaseFragment implements IAudioPlayerView {
    public static final String TAG = "AudioPlayerFragment";

    @Bind(R.id.btn_play_audio)
    protected Button playAudioBtn;
    private IPlayAudioPresenter playAudioPresenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        playAudioPresenter = new PlayAudioPresenter(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_audio_player;
    }

    public static AudioPlayerFragment newInstance() {
        return new AudioPlayerFragment();
    }

    @OnClick(R.id.btn_play_audio)
    protected void onPlayAudioButtonClicked() {
        AndroidUtils.hideKeyboard(getActivity());
        if (!isInternetAvailable()) {
            showNoInternetConnectionMessage();
            return;
        }
        playAudioPresenter.playAudioFile("abduction.wav");
    }

    @Override
    public void startAudioPlayerService(String absolutePath) {
        Intent i = new Intent(getContext(),AudioPlayerService.class);
        i.setAction(Constants.ACTION_PLAY_AUDIO);
        i.putExtra(AudioPlayerService.FILE_NAME_INTENT_EXTRA,absolutePath);
        getContext().startService(i);
    }

    @Override
    public String getFilePath(String filename) {
        File file = new File(getContext().getFilesDir(), filename);
        return file.getAbsolutePath();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playAudioPresenter.releaseResources();
    }
}
