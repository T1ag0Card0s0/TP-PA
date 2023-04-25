package pt.isec.pa.tinypac.model.fsm.game;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.fsm.game.states.*;

public enum EGameState {
    INITIAL_STATE,GAME_STARTED,GAME_WIN,GAME_OVER,GAME_PAUSED,VULNERABLE;

    public IGameState createState(GameContext context, GameManager gameManager) {
        return switch (this) {
            case INITIAL_STATE ->new InitialState(context,gameManager);
            case GAME_STARTED -> new GameStarted(context,gameManager);
            case GAME_WIN -> new GameWin(context,gameManager);
            case GAME_PAUSED -> new GamePaused(context,gameManager);
            case GAME_OVER -> new GameOver(context,gameManager);
            case VULNERABLE -> new Vulnerable(context,gameManager);
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
