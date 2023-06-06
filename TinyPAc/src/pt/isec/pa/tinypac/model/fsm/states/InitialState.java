package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.log.ModelLog;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class InitialState extends GameStateAdapter {
    public InitialState(GameContext context, Game game){
        super(context, game);
        ModelLog.getInstance().add(getGameState().toString());
    }
    @Override
    public void Down() {
        game.setNextDirection(2);
        changeState(EGameState.GAME_STARTED);
    }

    @Override
    public void Up() {
        game.setNextDirection(0);
        changeState(EGameState.GAME_STARTED);
    }

    @Override
    public void Left() {
        game.setNextDirection(3);
        changeState(EGameState.GAME_STARTED);
    }

    @Override
    public void Right() {
        game.setNextDirection(1);
        changeState(EGameState.GAME_STARTED);
    }

    @Override
    public boolean evolve(double time) {
        return false;
    }
    @Override
    public EGameState getGameState(){return EGameState.INITIAL_STATE;}
}

