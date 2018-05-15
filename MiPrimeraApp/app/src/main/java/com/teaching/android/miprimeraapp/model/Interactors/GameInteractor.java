package com.teaching.android.miprimeraapp.model.Interactors;

import com.teaching.android.miprimeraapp.R;
import com.teaching.android.miprimeraapp.model.GameModel;

import java.util.ArrayList;

public class GameInteractor {

    private static ArrayList<GameModel>games;

    /**
     * Constrctor
     */

    public GameInteractor (){
        //Si no tengo juegps , los creo
        if (games == null){
            GameModel smite = new GameModel(0,"smite","descripción smite",
                    "https://www.smitegame.com",R.drawable.smite_logo,R.drawable.susanoo);

            GameModel metin2 = new GameModel(1 , "metin2","descripción metin2",
                    "https://es.metin2.gameforge.com/",R.drawable.metinicon , R.drawable.fondometin);
            GameModel lol = new GameModel(2, "league of legends", "descripción lol",
                    "https://euw.leagueoflegends.com/es/", R.drawable.fondolol , R.drawable.lolicon );

            games = new ArrayList<>();
            games.add(smite);
            games.add(metin2);
            games.add(lol);
        }


    }

    public static ArrayList<GameModel> getGames() {
        return games;
    }
    public GameModel getGameWithId (int id){

        //obtener de ' game' el juego con el identificador 'id'
        for (GameModel game:games){
            if (game.getId()==id){
                return game ;
            }
        }
        return  null ;
    }
}
