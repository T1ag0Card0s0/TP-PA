package pt.isec.pa.tinypac.model.fsm.game;


public enum EGameState {
    INITIAL_STATE,GAME_STARTED,GAME_WIN,GAME_OVER,GAME_PAUSED,VULNERABLE;


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
