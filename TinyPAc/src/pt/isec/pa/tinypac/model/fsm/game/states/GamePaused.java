package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class GamePaused extends GameStateAdapter {
    public GamePaused(GameContext context, Game game) {
        super(context, game);
    }
    @Override
    public boolean KeyIsPressed(String s){
        if(s.equals(" ")){
            changeState(new GameStarted(context,game));
        }
        return true;
    }
    @Override
    public EGameState getGameState(){return EGameState.GAME_PAUSED;}
}
