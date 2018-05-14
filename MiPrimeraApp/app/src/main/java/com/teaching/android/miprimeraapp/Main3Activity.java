package com.teaching.android.miprimeraapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

public class Main3Activity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText ageEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Toolbar myToolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        usernameEditText = findViewById(R.id.user2);
        emailEditText = findViewById(R.id.email2);
        passwordEditText = findViewById(R.id.passoword2);
        ageEditText = findViewById(R.id.age);
        ageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //mostar el dialogo de las fechas
                    new DatePickerDialog(Main3Activity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            int anoActual= Calendar.getInstance().get(Calendar.YEAR);
                            int edad = anoActual - year ;
                            ageEditText.setText(String.valueOf(edad));
                        }
                    },1997 ,1,25).show();
                    }
                }
            });
        }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.guardar2:
                guardarDatos();
                break;
            case R.id.deleter :

                break;
        }
        return super.onOptionsItemSelected(item);


    }







    private void guardarDatos(){
        Log.d("activity_main3","Username"+ usernameEditText.getText());
        Log.d("actitivy_main3","Email"+emailEditText.getText());
        Log.d("actitivy_main3","Password"+passwordEditText.getText());
        Log.d("actitivy_main3","Age"+ageEditText.getText());

    }
    public void guardarDatos2 (View view){
        guardarDatos();
    }

    public void onDelete (View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.Confirmar);
        builder.setMessage(R.string.mensajeDialogo);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Main3Activity.this, "Borrando", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
