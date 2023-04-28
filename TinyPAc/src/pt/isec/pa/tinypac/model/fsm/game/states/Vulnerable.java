package pt.isec.pa.tinypac.model.fsm.game.states;

import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.fsm.game.EGameState;
import pt.isec.pa.tinypac.model.fsm.game.GameContext;
import pt.isec.pa.tinypac.model.fsm.game.GameStateAdapter;

public class Vulnerable extends GameStateAdapter {
    public Vulnerable(GameContext context, GameManager gameManager) {
        super(context, gameManager);
    }
    @Override
    public boolean WinLevel() {
        if(gameManager.thereIsFood()){
            if(gameManager.LastLevel()){
                gameManager.changelevel();
                changeState(new InitialState(context,gameManager));
            }else{
                changeState(new GameWin(context,gameManager));
            }
        }
        return true;
    }
    @Override
    public boolean beVulnerable(long interval) {
        if(gameManager.endOfVulnerability(interval)){
            changeState(new GameStarted(context,gameManager));
            return false;
        }
        return true;
    }

    @Override
    public boolean KeyIsPressed(String keyPressed){
        if(keyPressed.equals(" "))changeState(new GamePaused(context,gameManager));

        if(keyPressed.equals("Escape"))changeState(new GameOver(context,gameManager));

        gameManager.setPacmanNextDirection(keyPressed);
        return true;
    }
    @Override
    public EGameState getGameState(){
        return EGameState.VULNERABLE;
    }
}
