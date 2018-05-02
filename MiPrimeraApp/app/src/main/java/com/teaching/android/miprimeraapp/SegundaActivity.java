package com.teaching.android.miprimeraapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent =getIntent();
        super.onCreate(savedInstanceState);
        String id = intent.getStringExtra("id");
        setContentView(R.layout.activity_segunda);
        Log.d("Segundo Activity","el extra de Paco");
    }
    public void onClick2(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(intent);
    }

    public void onClick3(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:1234") ) ;
        startActivity(intent);
    }
}
