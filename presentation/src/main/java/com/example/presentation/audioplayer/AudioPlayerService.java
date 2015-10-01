package com.example.presentation.audioplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.config.Constants;

/**
 *
 */
public class AudioPlayerService extends Service {

    private MediaPlayer mediaPlayer;
    String path;
    public final static String FILE_NAME_INTENT_EXTRA = "AUDIO_FILE_PATH";
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();

    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(Constants.ACTION_PLAY_AUDIO)) {

            path = intent.getExtras().getString(FILE_NAME_INTENT_EXTRA);

            try {
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopSelf();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return START_STICKY;
    }


    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public IBinder onBind(Intent objIndent) {
        return null;
    }

}
