package com.teaching.android.miprimeraapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.teaching.android.miprimeraapp.BaseDatos.GameInteractorCallback;
import com.teaching.android.miprimeraapp.BaseDatos.MyConnectingReceive;
import com.teaching.android.miprimeraapp.model.Interactors.GameInteractor;
import com.teaching.android.miprimeraapp.model.Interactors.GamesFirebaseInteractor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.sql.Connection;


public class Listmain extends AppCompatActivity {

    private MyAdapter myAdapter;
    private ListView listView;
    private GamesFirebaseInteractor gamesFirebaseInteractor;
    private MyConnectingReceive myConnectingReceive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmain);
        listView = findViewById(R.id.lista);
        //empieza a escuchar si hay internet
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new MyConnectingReceive(), intentFilter);

        ConnectivityManager conectado = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo conectadoNetworkinfo = conectado.getActiveNetworkInfo();
        if (conectadoNetworkinfo != null
                && conectadoNetworkinfo.isConnectedOrConnecting()) {
            gamesFirebaseInteractor = new GamesFirebaseInteractor();

            gamesFirebaseInteractor.getGames(new GameInteractorCallback() {
                @Override
                public void onGamesAvailable() {
                    findViewById(R.id.Loading).setVisibility(View.GONE);
                    //Aqui GameFirebaseInteractor ya tiene la lista de juegos
                    myAdapter = new MyAdapter();
                    listView.setAdapter(myAdapter);

                }
            });
        } else {
            findViewById(R.id.Loading).setVisibility(View.GONE);
            Toast.makeText(this, "No hay internet", Toast.LENGTH_LONG).show();
        }






        /*
        StringRequest myStringRequest =new StringRequest(Request.Method.GET, "https://miprimeraapp-683eb.firebaseio.com/games.json", new Response.Listener<String>() {
          //  @Override
            public void onResponse(String response) {
                Log.d("ListActivity", "Responde is : " + response);
                try {
                    JSONArray myArray = new JSONArray(response);
                }

            }catch
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ) ;
        RequestQueue myQueue = Volley.newRequestQueue(this);
        myQueue.add(myStringRequest);
        */

        File directorioInterno = getFilesDir();
        File directorioExterno = getCacheDir();
        Log.d("Listmain", "interno" + directorioInterno.getAbsolutePath());
        Log.d("Listmain", "Cache" + directorioExterno.getAbsolutePath());

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Listmain.this, "Seleccionada posición " + position,
                        Toast.LENGTH_LONG).show();
                //Abrir activity de detalle
                Intent intent = new Intent(Listmain.this, gameDetailActivity.class);

                startActivity(intent);
                int gameId = new GameInteractor().getGames().get(position).getId();
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });
        getExternalFilesDir(null);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // Tenemos permisos
            obtenerUbicacion();

        } else {
            // No tenemos permisos
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    100);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //El usuario acepta el permiso
                obtenerUbicacion();
            } else {
                //El usuario deniega los permiso
            }
        }
    }

    @SuppressLint("MissingPermission")
    public void obtenerUbicacion() {
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                    Log.d("Location Changed", location.toString());


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent registerIntent = new Intent(this,Loginactivity.class);
        startActivity(registerIntent);
        return true;
    }

    private class MyAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                return gamesFirebaseInteractor.getGames().size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.plantilla,parent , false);

                ImageView icon =rowView.findViewById(R.id.icon);

                Glide.with(Listmain.this).load(gamesFirebaseInteractor.getGames().get(position).getIcon()).into(icon);
                //icon.setImageResource(imagenlis[position]);

                TextView textView = rowView.findViewById(R.id.text);
                textView.setText(gamesFirebaseInteractor.getGames().get(position).getName());
                return rowView;
            }


        }
    }

