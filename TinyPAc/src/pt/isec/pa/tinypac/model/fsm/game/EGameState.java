package pt.isec.pa.tinypac.model.fsm.game;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.game.states.*;

public enum EGameState {
    INITIAL_STATE,GAME_STARTED,GAME_WIN,GAME_OVER,GAME_PAUSED,VULNERABLE;

    public IGameState createState(GameContext context, Game game) {
        return switch (this) {
            case INITIAL_STATE ->new InitialState(context,game);
            case GAME_STARTED -> new GameStarted(context,game);
            case GAME_WIN -> new GameWin(context,game);
            case GAME_PAUSED -> new GamePaused(context,game);
            case GAME_OVER -> new GameOver(context,game);
            case VULNERABLE -> new Vulnerable(context,game);
            default -> null;
        };
    }
    @Override
    public String toString() {
        return(
                switch (this){
                    case INITIAL_STATE -> "Game Initialized";
                    case GAME_STARTED -> "Game Started";
                    case GAME_PAUSED -> "Game Paused";
                    case GAME_WIN -> "Game Win";
                    case GAME_OVER -> "Game Over";
                    case VULNERABLE -> "Vulnerable";
                }
        );
    }
}
