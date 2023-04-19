package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class InitialState extends GameStateAdapter   {
    public InitialState(GameContext context, Game game) {
        super(context, game);
        game.initGame();
    }
    @Override
    public boolean DirectionKeyIsPressed(String keyPressed) {
        if(!keyPressed.equals("Character")) {
            game.setPacmanNextDirection(keyPressed);
            changeState(new GameStarted(context, game));
            return true;
        }
        return false;
    }
    @Override
    public EGameState getGameState(){
        return EGameState.INITIAL_STATE;
    }

}
