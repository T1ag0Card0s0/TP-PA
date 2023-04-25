package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class GameOver extends GameStateAdapter {
    public GameOver(GameContext context, GameManager gameManager) {
        super(context, gameManager);
    }

    @Override
    public EGameState getGameState(){return EGameState.GAME_OVER;}
}
