package com.teaching.android.miprimeraapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.teaching.android.miprimeraapp.Fragments.GameDetailFragment;
import com.teaching.android.miprimeraapp.Presenter.GameDetalPresenter;
import com.teaching.android.miprimeraapp.model.GameModel;

import view.GameDetailView;

public class gameDetailActivity extends AppCompatActivity implements GameDetailView {

    private GameDetalPresenter presenter ;
    private int currenPosition ;
    private MyPagerAdapter  myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        Toolbar myToolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(myToolbar);
        presenter = new GameDetalPresenter();
        currenPosition= getIntent().getIntExtra("position",0);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    protected void onStart(){
        super.onStart();
        presenter.startPresenting(this);

        final ViewPager myViewPager = findViewById(R.id.view_pager);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myPagerAdapter);
        myViewPager.setCurrentItem(currenPosition);
        getSupportActionBar().setTitle(myPagerAdapter.getPageTitle(currenPosition));
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Cambio el titulo de la toolbar con el que tiene por defecto
                getSupportActionBar().setTitle(myPagerAdapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onGameLoaded(GameModel game) {

    }private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "";//presenter.getGames().get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {
            int gameId = presenter.getGames().get(position).getId();
            return GameDetailFragment.newInstance(gameId);
        }

        @Override
        public int getCount() {
            return presenter.getGames().size();
        }
    }




}
