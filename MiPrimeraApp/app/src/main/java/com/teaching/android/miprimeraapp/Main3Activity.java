package com.teaching.android.miprimeraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class Main3Activity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText ageEditText;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        usernameEditText = findViewById(R.id.user2);
        emailEditText = findViewById(R.id.email2);
        passwordEditText = findViewById(R.id.passoword2);
        ageEditText = findViewById(R.id.age);
    }
    public void guardarDatos(View view){
        Log.d("activity_main3","Username"+ usernameEditText.getText());
        Log.d("actitivy_main3","Email"+emailEditText.getText());
        Log.d("actitivy_main3","Password"+passwordEditText.getText());
        Log.d("actitivy_main3","Age"+ageEditText.getText());

    }
}
