package com.teaching.android.miprimeraapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity {
    private MediaPlayer myMediaPlayer3 ;
    private boolean musica=false  ;
    int pordondeva=0;
   private VideoView video ;
   private int contar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_player);

        //Para reproduccir musica

        //myMediaPlayer3 = MediaPlayer.create(this , R.raw.cancion);
         //myMediaPlayer3.start();
         video = findViewById(R.id.Video_reproducir);
         video.setVideoURI (Uri.parse("https://img-9gag-fun.9cache.com/photo/aBxGoNN_460sv.mp4"));
         MediaController myMediaController = new MediaController(this);
         myMediaController.setAnchorView(video);
         video.setMediaController(myMediaController);
                video.start();
                new ContadorAsynTask().execute();


    }
    private class ContadorAsynTask extends AsyncTask<Void,Integer , Integer>{

        @Override
        protected Integer doInBackground(Void... voids) {
            contar = 0 ;
            do{
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("contar","valor:"+contar);
            } while (contar++<=100);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            contar = 0 ;
            do{
                Log.d("contar","valor:"+contar);
            } while (contar++<100);

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("contar2","Valor final"+contar);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        myMediaPlayer3.release();
        myMediaPlayer3 = null;

    }
    public void Empezar_musica (View view){
        Intent myIntent = new Intent(this, MediaPlayerService.class);
        startService(myIntent);



    }
    public void Pausar_musica (View view){
        Intent myIntent = new Intent(this, MediaPlayerService.class);
        stopService(myIntent);

    }
}
