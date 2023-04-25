package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class InitialState extends GameStateAdapter   {
    public InitialState(GameContext context, GameManager gameManager) {
        super(context, gameManager);
        gameManager.initGame();
        System.out.println("passei aqui");
    }
    @Override
    public boolean KeyIsPressed(String keyPressed) {
        if(keyPressed.length()>1) {
            context.registEngineClients();
            gameManager.setPacmanNextDirection(keyPressed);
            changeState(new GameStarted(context, gameManager));
            return true;
        }
        return false;
    }
    @Override
    public EGameState getGameState(){
        return EGameState.INITIAL_STATE;
    }

}
