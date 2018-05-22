package com.teaching.android.miprimeraapp;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.teaching.android.miprimeraapp.BaseDatos.AppDatabase;
import com.teaching.android.miprimeraapp.BaseDatos.User;

import java.io.File;
import java.sql.SQLClientInfoException;
import java.util.Calendar;

public class main3activity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText ageEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Log.d("Listmain", "Existe y puedo escriber?" + isExternalStorageWritable());
        Log.d("Listmain", "Existe y puedo escriber?" + isExternalStorageReadable());

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
                    new DatePickerDialog(main3activity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            int anoActual = Calendar.getInstance().get(Calendar.YEAR);
                            int edad = anoActual - year;
                            ageEditText.setText(String.valueOf(edad));
                        }
                    }, 1997, 1, 25).show();
                }
            }

        });
        File imgFile = new File(getExternalFilesDir(null), "matt-icons_preferences-desktop-personal.png");
        if (isExternalStorageReadable()) {
            if (imgFile.exists()) {
                ImageView myImage = findViewById(R.id.imagenPerfil);
                myImage.setImageURI(Uri.fromFile(imgFile));
            }
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.guardar2:

                break;
            case R.id.deleter:

                break;
        }
        return super.onOptionsItemSelected(item);


    }


    public void guardarDatos(View view) {
        Log.d("activity_main3", "Username" + usernameEditText.getText());
        Log.d("actitivy_main3", "Email" + emailEditText.getText());
        Log.d("actitivy_main3", "Password" + passwordEditText.getText());
        Log.d("actitivy_main3", "Age" + ageEditText.getText());

        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.user_main3), Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreference.edit();
        myEditor.putString("nombre", usernameEditText.getText().toString());
        myEditor.putString("contrasena", passwordEditText.getText().toString());
        myEditor.putString("Email", emailEditText.getText().toString());
        myEditor.putString("años", ageEditText.getText().toString());
        myEditor.apply();

        AppDatabase base = Room.databaseBuilder(getApplicationContext(),AppDatabase.class ,"base")
                .allowMainThreadQueries().build();

        try {
            User user =new User ();
            base.userDao().insert(user);
        }catch (SQLiteConstraintException ex){

        }
        Toast.makeText(main3activity.this , "Tú usuario está guardado",
                Toast.LENGTH_LONG).show();
    }


    public void onDelete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.Confirmar);
        builder.setMessage(R.string.mensajeDialogo);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(main3activity.this, "Borrando", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences mySharedPreferences = getSharedPreferences(getString(R.string.user_main3), Context.MODE_PRIVATE);
        String Username = mySharedPreferences.getString("nombre", "");
        String Pasword = mySharedPreferences.getString("contrasena", "");
        String email = mySharedPreferences.getString("Email", "");
        String Age = mySharedPreferences.getString("años", "");
        usernameEditText.setText(Username);
        passwordEditText.setText(Pasword);
        emailEditText.setText(email);
        ageEditText.setText(Age);

    }

    // Checks if external storage is available for read and write
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    // Checks if external storage is available to at least read
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}
