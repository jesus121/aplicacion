package com.teaching.android.miprimeraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ViewHolderPactice.MyRecycleViewAdapter;

public class RecyclerViewAcMvity extends AppCompatActivity {
    private RecyclerView myRecycleView;
    private MyRecycleViewAdapter myRecycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_ac_mvity);
        myRecycleView = findViewById(R.id.recycle_view);

        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(this);
        myRecycleView.setLayoutManager(myLayoutManager);

        myRecycleViewAdapter = new MyRecycleViewAdapter(getResources().getStringArray(R.array.colors));

        myRecycleView.setAdapter(myRecycleViewAdapter);
    }
}
