package com.teaching.android.miprimeraapp.Presenter;

import com.teaching.android.miprimeraapp.model.GameModel;
import com.teaching.android.miprimeraapp.model.Interactors.GameInteractor;

import java.util.ArrayList;

import view.GameDetailView;

public class GameDetalPresenter {
    private GameInteractor interactor ;
    private GameDetailView view ;

    public  void startPresenting (GameDetailView view){
        this.view = view;
        interactor = new GameInteractor();
    }
    public void loadGameWithId (int id){
        GameModel game = interactor.getGameWithId(id);
        view.onGameLoaded(game);
    }
    public ArrayList<GameModel>getGames(){
        return interactor.getGames();
    }
}
