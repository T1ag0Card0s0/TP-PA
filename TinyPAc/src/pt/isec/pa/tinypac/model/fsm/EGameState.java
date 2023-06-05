package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.fsm.states.GamePaused;
import pt.isec.pa.tinypac.model.fsm.states.InitialState;
import pt.isec.pa.tinypac.model.fsm.states.Vulnerable;
import pt.isec.pa.tinypac.model.fsm.states.GameStarted;

public enum EGameState {
    INITIAL_STATE,GAME_STARTED,GAME_PAUSED,VULNERABLE;


    @Override
    public String toString() {
        return(
                switch (this){
                    case INITIAL_STATE -> "Game Initialized";
                    case GAME_STARTED -> "Game Started";
                    case GAME_PAUSED -> "Game Paused";
                    case VULNERABLE -> "Vulnerable";
                }
        );
    }
    public IGameState createState(GameContext context, Game game){
        return(
                switch (this){
                    case INITIAL_STATE -> new InitialState(context,game);
                    case GAME_STARTED -> new GameStarted(context,game);
                    case GAME_PAUSED -> new  GamePaused(context,game);
                    case VULNERABLE -> new Vulnerable(context,game);
                }
        );
    }
}