package com.teaching.android.miprimeraapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;


public class Loginactivity extends AppCompatActivity {
    private EditText username2EditText ;
    private EditText passwordEditText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        Toolbar myToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);

        username2EditText = findViewById(R.id.username2);
        passwordEditText = findViewById(R.id.passoword4);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences mySharedPreferences = getSharedPreferences(getString(R.string.user_prefences),Context.MODE_PRIVATE);
        String savedUsername = mySharedPreferences.getString("username_key","");
        username2EditText.setText(savedUsername);
    }


    public void onCancel(View view) {
        username2EditText.setText("");
        passwordEditText.setText("");
    }

    public void doRegister(View view) {
        Intent registerIntent = new Intent(this,main3activity.class);
        startActivity(registerIntent);
    }

    public void doLogin(View view) {
        //obtiene los valores
        String username = username2EditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(TextUtils.isEmpty(username)){
            username2EditText.setError("el campo está vacío");
        }else if (TextUtils.isEmpty(password)){
            passwordEditText.setError("está vacío");
            passwordEditText.setError("este campo está vacío");
        }else {
            SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.user_prefences),Context.MODE_PRIVATE);
            SharedPreferences.Editor myEditor = mySharedPreference.edit() ;
            myEditor.putString("username_key",username);
            myEditor.apply();

            Intent profileIntent = new Intent(this ,main3activity.class);
                startActivity(profileIntent);
        }
    }

}
