package com.teaching.android.miprimeraapp;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class MediaPlayerService extends Service {
    private MediaPlayer myMediaPlayer3 ;

    public MediaPlayerService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = "https://firebasestorage.googleapis.com/v0/b/miprimeraapp-db818.appspot.com/o/crowd-cheering.mp3?alt=media&token=1e1a8341-f108-460c-b3a7-8f19097040e3";
        myMediaPlayer3 = new MediaPlayer();
        myMediaPlayer3.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            myMediaPlayer3.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myMediaPlayer3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        myMediaPlayer3.prepareAsync();
        myMediaPlayer3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopSelf();
            }
        });
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       myMediaPlayer3.release();
       myMediaPlayer3 = null;
    }


    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
