package com.teaching.android.miprimeraapp.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.teaching.android.miprimeraapp.R;
import com.teaching.android.miprimeraapp.Webview.webViewActivity;
import com.teaching.android.miprimeraapp.model.GameModel;
import com.teaching.android.miprimeraapp.model.Interactors.GameInteractor;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameDetailFragment extends Fragment {


    public GameDetailFragment() {
        // Required empty public constructor
    }

    public static GameDetailFragment newInstance(int gameId){
        GameDetailFragment fragment = new GameDetailFragment();

        Bundle MyBundle = new Bundle();
        MyBundle.putInt("gameid",gameId);
        fragment.setArguments(MyBundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView =  inflater.inflate(R.layout.fragment_game_detail, container, false);

        //Obtener GameModel de GameInteractor
        int gameId = getArguments().getInt("gameid",0);
        final GameModel game = new GameInteractor().getGameWithId(gameId);

        // UPDATE VIEW WITH GAME MODEL DATA
        ImageView icono = fragmentView.findViewById(R.id.smitelogo);
        icono.setImageResource(game.getIconDrawable());
        //Cambiar el fondo de la imagen
        ImageView fondo = fragmentView.findViewById(R.id.fondosmite);
        fondo.setImageResource(game.getBackgroundDrawable());

        //Descripción
        TextView description =fragmentView.findViewById(R.id.text3);
        description.setText(game.getDescription());

        Button boton = fragmentView.findViewById(R.id.website_button);
        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent webIntent = new Intent(getContext(),webViewActivity.class);
                webIntent.putExtra("url",game.getOfficialWebsiteURL());
                startActivity(webIntent);
               // Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(game.getOfficialWebsiteURL()));
                //startActivity(webIntent) ;



            }
        });

        return fragmentView;
    }


}
