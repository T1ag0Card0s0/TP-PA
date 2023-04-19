package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class GameStarted extends GameStateAdapter {
    public GameStarted(GameContext context, Game game) {
        super(context, game);
    }
    @Override
    public boolean DirectionKeyIsPressed(String keyPressed){
        game.setPacmanNextDirection(keyPressed);
        return true;
    }
    @Override
    public boolean LostCurrentLevel() {
        changeState(new InitialState(context,game));
        return false;
    }
    @Override
    public boolean PauseGame() {
        changeState(new GamePaused(context,game));
        return true;
    }
    @Override
    public boolean WinLevel() {
        changeState(new InitialState(context,game));
        return false;
    }

    @Override
    public boolean WinGame() {
        changeState(new GameWin(context,game));
        return false;
    }
    @Override
    public EGameState getGameState(){
        return EGameState.GAME_STARTED;
    }

}
