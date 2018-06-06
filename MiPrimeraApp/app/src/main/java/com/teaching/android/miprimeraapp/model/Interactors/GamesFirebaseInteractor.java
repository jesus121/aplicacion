package com.teaching.android.miprimeraapp.model.Interactors;

import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.teaching.android.miprimeraapp.BaseDatos.GameInteractorCallback;
import com.teaching.android.miprimeraapp.model.GameModel;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class GamesFirebaseInteractor {
    private ArrayList<GameModel> games = new ArrayList<>();


    public void Gameinteractor2() {
    }

    public void getGames(final GameInteractorCallback callback) {
        //1-llamar a Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myReference = firebaseDatabase.getReference("games");

        myReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot nodoJuego : dataSnapshot.getChildren()) {
                    GameModel model = nodoJuego.getValue(GameModel.class);
                    Log.d("Firebase Interactor", "Game: " + model.getName());
                    games.add(model);
                }
                callback.onGamesAvailable();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        String token = FirebaseInstanceId.getInstance().getToken();
        myReference = firebaseDatabase.getReference("device_token") ;
        myReference.setValue(token);
    }


    public ArrayList<GameModel> getGames() {
        return games;
    }

    public GameModel getGameWithId(int id) {

        //obtener de ' game' el juego con el identificador 'id'
        for (GameModel game : games) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }


}

