package com.teaching.android.miprimeraapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teaching.android.miprimeraapp.BaseDatos.GameInteractorCallback;
import com.teaching.android.miprimeraapp.Listmain;
import com.teaching.android.miprimeraapp.R;
import com.teaching.android.miprimeraapp.Webview.webViewActivity;
import com.teaching.android.miprimeraapp.model.GameModel;
import com.teaching.android.miprimeraapp.model.Interactors.GameInteractor;
import com.teaching.android.miprimeraapp.model.Interactors.GamesFirebaseInteractor;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameDetailFragment extends Fragment {

    private GamesFirebaseInteractor gamesFirebaseInteractor;


    public GameDetailFragment() {
        // Required empty public constructor
    }

    public static GameDetailFragment newInstance(int gameId) {
        GameDetailFragment fragment = new GameDetailFragment();

        Bundle MyBundle = new Bundle();
        MyBundle.putInt("gameid", gameId);
        fragment.setArguments(MyBundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_game_detail, container, false);

        //Obtener GameModel de GameInteractor
        final int gameId = getArguments().getInt("gameid", 0);
        gamesFirebaseInteractor = new GamesFirebaseInteractor();
        gamesFirebaseInteractor.getGames(new GameInteractorCallback() {
            @Override
            public void onGamesAvailable() {
                final GameModel game = gamesFirebaseInteractor.getGameWithId(gameId);

                // UPDATE VIEW WITH GAME MODEL DATA
                ImageView icono = getView().findViewById(R.id.smitelogo);
                Glide.with(getView()).load(game.getIcon()).into(icono);
                //icono.setImageResource(game.getIcon());
                //Cambiar el fondo de la imagen
                ImageView fondo = getView().findViewById(R.id.fondosmite);
                Glide.with(getView()).load(game.getBackground()).into(fondo);
                //fondo.setImageResource(game.getBackground());

                //Descripción
                TextView description = getView().findViewById(R.id.text3);
                description.setText(game.getDescription());

                Button boton = getView().findViewById(R.id.website_button);
                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent webIntent = new Intent(getContext(), webViewActivity.class);
                        webIntent.putExtra("url", game.getOfficialWebsiteURL());
                        startActivity(webIntent);
                        // Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(game.getOfficialWebsiteURL()));
                        //startActivity(webIntent) ;
                    }
                });


            }
        });

        return fragmentView;
    }


}
