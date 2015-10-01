package com.example.presentation.audioplayer;

import android.os.Bundle;

import com.example.androidarchitecture.R;
import com.example.presentation.framework.BaseActivity;

/**
 *
 */
public class AudioPlayerActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            attachToActivity(AudioPlayerFragment.newInstance(), AudioPlayerFragment.TAG);
        }

        setTitle(getString(R.string.audio_player));
    }
}
