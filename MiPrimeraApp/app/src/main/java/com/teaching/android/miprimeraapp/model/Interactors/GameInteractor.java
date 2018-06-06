package com.teaching.android.miprimeraapp.model.Interactors;

import com.teaching.android.miprimeraapp.R;
import com.teaching.android.miprimeraapp.model.GameModel;

import java.util.ArrayList;

public class GameInteractor {

    private  ArrayList<GameModel>games;

    /**
     * Constructor
     */

    public GameInteractor (){
        //Si no tengo juegps , los creo
        if (games == null){
            GameModel smite = new GameModel(0,"smite","descripción smite",
                    "https://www.smitegame.com","","");

            GameModel metin2 = new GameModel(1 , "metin2","descripción metin2",
                    "https://es.metin2.gameforge.com/","" , "");


            games = new ArrayList<>();
            games.add(smite);
            games.add(metin2);

        }


    }

    public  ArrayList<GameModel> getGames() {
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
