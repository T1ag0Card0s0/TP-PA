package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.Game;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.data.elements.moveableElements.PacMan;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class GamePaused extends GameStateAdapter {
    public GamePaused(GameContext context, GameManager gameManager) {
        super(context, gameManager);
        this.context.pauseGameEngine();
    }
    @Override
    public boolean KeyIsPressed(String s){
        if(s.equals(" ")){
            context.resumeGameEngine();
            changeState(new GameStarted(context,gameManager));
        }
        return true;
    }
    @Override
    public EGameState getGameState(){return EGameState.GAME_PAUSED;}
}
