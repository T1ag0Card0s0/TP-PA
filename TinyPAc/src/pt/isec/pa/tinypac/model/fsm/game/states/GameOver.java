package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class GameOver extends GameStateAdapter {
    public GameOver(GameContext context, Game game) {
        super(context, game);
    }

    @Override
    public boolean waitForTheEnd(){
        context.waitForTheEnd();
        return true;
    }
    @Override
    public EGameState getGameState(){return EGameState.GAME_OVER;}
}
