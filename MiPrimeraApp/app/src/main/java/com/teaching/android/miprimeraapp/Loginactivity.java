package com.teaching.android.miprimeraapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        username2EditText = findViewById(R.id.username2);
        passwordEditText = findViewById(R.id.passoword2);
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
            username2EditText.setError("El usuario está vacio");
        }else if (TextUtils.isEmpty(password)){
            passwordEditText.setError("está vacío");
            Intent maninIntent = new Intent(this ,MainActivity.class);
            startActivity(maninIntent);
        }
    }
}
