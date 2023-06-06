package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.log.ModelLog;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class GamePaused extends GameStateAdapter {
    public GamePaused(GameContext context, Game game){
        super(context,game);
        ModelLog.getInstance().add(getGameState().toString());
    }
    @Override
    public boolean resume() {
        changeState(context.getLastState());
        return true;
    }
    @Override
    public boolean evolve(double time) {
        return false;
    }
    @Override
    public EGameState getGameState(){return EGameState.GAME_PAUSED;}
}
