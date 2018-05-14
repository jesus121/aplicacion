package com.teaching.android.miprimeraapp;

import android.content.Intent;
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
        passwordEditText = findViewById(R.id.passoword2);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    public void onCancel(View view) {
        username2EditText.setText("");
        passwordEditText.setText("");
    }

    public void doRegister(View view) {
        Intent registerIntent = new Intent(this,Main3Activity.class);
        startActivity(registerIntent);
    }

    public void doLogin(View view) {
        //obtiene los valores
        String username = username2EditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(TextUtils.isEmpty(username)){
            //corregir
            username2EditText.setError("el campo está vacío");
        }else if (TextUtils.isEmpty(password)){
            passwordEditText.setError("está vacío");
            passwordEditText.setError("este campo está vacío");
        }else {
            Intent profileIntent = new Intent(this ,Main3Activity.class);
                startActivity(profileIntent);
        }
    }
}
