package com.teaching.android.miprimeraapp;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
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


        AppDatabase base = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "base")
                .allowMainThreadQueries().build();


        try {
            User user = new User();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            user.setAge(ageEditText.getText().toString());
            base.userDao().insert(user);
            Intent intentperfil = new Intent(main3activity.this, Loginactivity.class);
            startActivity(intentperfil);
        } catch (SQLiteConstraintException ex) {
            Toast.makeText(main3activity.this, "Lo siento tu usuario ya existe",
                    Toast.LENGTH_LONG).show();
        }
        Toast.makeText(main3activity.this, "has creado con extito tu perfil",
                Toast.LENGTH_LONG).show();


        SharedPreferences mySharedPreference = getSharedPreferences(getString(R.string.user_main3), Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreference.edit();
        myEditor.putString("nombre", usernameEditText.getText().toString());

        myEditor.apply();
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
        String usernameValue = mySharedPreferences.getString("username_key", "");
        AppDatabase myDatabase= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"base").allowMainThreadQueries().build();
        User myUser =myDatabase.userDao().findByUsername(usernameValue);
        if (myUser !=null){
            usernameEditText.setText(myUser.getUsername());
            emailEditText.setText(myUser.getEmail());
            ageEditText.setText(myUser.getAge());
            passwordEditText.setText(myUser.getPassword());

        }


    }
    private File createImageFile (){
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(storageDir , "main3activity.png");
    }

    public void imagenPerfil2 (View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager())!=null){
            File phothoFile = createImageFile();
            Uri photoUri = FileProvider.getUriForFile(this , "com.teaching.android.miprimeraapp",phothoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT , photoUri);
            startActivityForResult(takePictureIntent,100);

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        File myFile = createImageFile();

    }
}
