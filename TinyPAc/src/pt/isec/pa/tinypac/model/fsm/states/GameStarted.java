package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

public class GameStarted extends GameStateAdapter {
    public GameStarted(GameContext context, Game game){
        super(context, game);
    }

    @Override
    public void Down() {
        game.setNextDirection(2);
    }

    @Override
    public void Up() {
        game.setNextDirection(0);
    }

    @Override
    public void Left() {
        game.setNextDirection(3);
    }

    @Override
    public void Right() {
        game.setNextDirection(1);
    }

    @Override
    public boolean evolve(double time) {
        if(game.levelEnded()){
            changeState(EGameState.INITIAL_STATE);
        }else if(game.pacmanHasPower()){
            changeState(EGameState.VULNERABLE);
        }
        return true;
    }

    @Override
    public boolean pause() {
        changeState(EGameState.GAME_PAUSED);
        return true;
    }
    @Override
    public EGameState getGameState(){return EGameState.GAME_STARTED;}
}
