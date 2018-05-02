package com.teaching.android.miprimeraapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity" , "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity" , "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity" , "onSResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity" , "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity" , "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity" , "onDestroy");
    }

    public void onClick(View view) {
        Intent intent =new Intent(this,SegundaActivity.class);
        intent.putExtra("id","Android Roolz");
        startActivity(intent);

    }

}
