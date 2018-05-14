package com.teaching.android.miprimeraapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Listmain extends AppCompatActivity {

    String[]gamelist = {"smite","metin2","Leage of Legends"};
    int []imagenlis = {R.drawable.smiteicon,R.drawable.metinicon,R.drawable.lolicon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmain);
        ListView listView = findViewById(R.id.lista);
        listView.setAdapter(new MyAdapter());

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(Listmain.this, "Seleccionada posición " + position,
                       Toast.LENGTH_LONG).show();
           }
       });

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
                return gamelist.length;
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
                icon.setImageResource(imagenlis[position]);

                TextView textView = rowView.findViewById(R.id.text);
                textView.setText(gamelist[position]);
                return rowView;
            }


        }
    }

